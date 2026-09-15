package com.bianchiigoa.perfume_recommender.loader;
import com.bianchiigoa.perfume_recommender.model.*;
import com.bianchiigoa.perfume_recommender.service.*;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

//@Component
public class CargadorDatos implements CommandLineRunner {


    @Autowired
    private MarcaService marcaService;

    private final PerfumeService perfumeService;
    private final NotaPerfumeService notaPerfumeService;
    private final NotaService notaService;
    private final PerfumeAcordeService perfumeAcordeService;
    private final AcordeService acordeService;
    public CargadorDatos(PerfumeService perfumeService, NotaPerfumeService notaPerfumeService, NotaService notaService, PerfumeAcordeService perfumeAcordeService, AcordeService acordeService){
      this.perfumeService = perfumeService;
      this.notaPerfumeService = notaPerfumeService;
      this.notaService = notaService;
      this.perfumeAcordeService = perfumeAcordeService;
      this.acordeService = acordeService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Abrir el CSV desde resources
        ClassPathResource recurso = new ClassPathResource("final-dataset-09-09.csv");
        Reader reader = new InputStreamReader(recurso.getInputStream());
        CSVReader csvReader = new CSVReader(reader);

        String[] fila;
        boolean primera = true;

        while ((fila = csvReader.readNext()) != null) {
            if (primera) {          // saltear encabezados
                primera = false;
                continue;
            }
            // dentro del while, después de saltear encabezados:

// 1. conseguir la marca
            Marca marca = marcaService.buscarOCrearMarca(fila[1]);

// 2. parsear los datos del perfume
            String nombre = fila[0];
            Genero genero = parsearGenero(fila[2]);
            BigDecimal ratingValue = parsearRating(fila[3]);
            Integer ratingCount = parsearEntero(fila[4]);
            Integer anio = parsearEntero(fila[5]);

// 3. crear el perfume
           Perfume perfume =  perfumeService.crearPerfume(marca, nombre, genero, anio, ratingValue, ratingCount);
            procesarNotas(fila[6], perfume, NivelPiramide.top);
            procesarNotas(fila[7], perfume, NivelPiramide.heart);
            procesarNotas(fila[8], perfume, NivelPiramide.base);
            procesarAcordes(fila, perfume);

        }

        csvReader.close();
        System.out.println(">>> Procesadas todas las filas");
    }

    private BigDecimal parsearRating(String valor) {
        if (valor == null || valor.isBlank()) return null;
        return new BigDecimal(valor.replace(",", "."));  // "1,42" → "1.42" → 1.42
    }

    private Integer parsearEntero(String valor) {
        if (valor == null || valor.isBlank()) return null;
        return Integer.parseInt(valor.trim());
    }

    private Genero parsearGenero(String valor) {
        if (valor == null) return null;
        return switch (valor.trim().toLowerCase()) {
            case "men" -> Genero.masculino;
            case "women" -> Genero.femenino;
            case "unisex" -> Genero.unisex;
            default -> null;  // por si viene algo raro
        };
    }
    private void procesarNotas(String celda, Perfume perfume, NivelPiramide nivel) {
        if (celda == null || celda.isBlank()) return;

        Set<String> notasUnicas = new HashSet<>();   // ← para evitar repetidas
        for (String nombreNota : celda.split(",")) {
            String limpio = nombreNota.trim().toLowerCase();
            if (limpio.isEmpty()) continue;
            if (!notasUnicas.add(limpio)) continue;   // ← si ya estaba, saltear
            Nota nota = notaService.buscarOCrearNota(limpio);
            notaPerfumeService.addNotaPerfume(perfume, nota, nivel);
        }
    }

    private void procesarAcordes(String[] fila, Perfume perfume) {
        int[] columnas = {9, 10, 11, 12, 13};   // mainaccord1..5
        for (int i = 0; i < columnas.length; i++) {
            String nombreAcorde = fila[columnas[i]];
            if (nombreAcorde == null || nombreAcorde.isBlank()) continue;
            Acorde acorde = acordeService.buscarOCrearAcorde(nombreAcorde.trim());
            perfumeAcordeService.addPerfumeAcorde(i + 1,perfume, acorde);  // orden 1..5
        }
    }
}

