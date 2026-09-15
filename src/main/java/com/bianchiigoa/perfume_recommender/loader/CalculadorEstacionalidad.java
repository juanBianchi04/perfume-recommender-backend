package com.bianchiigoa.perfume_recommender.loader;

import com.bianchiigoa.perfume_recommender.model.*;
import com.bianchiigoa.perfume_recommender.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Component
public class CalculadorEstacionalidad implements CommandLineRunner {

    private final PerfumeRepository perfumeRepository;
    private final PerfumeAcordeRepository perfumeAcordeRepository;
    private final AcordeEstacionRepository acordeEstacionRepository;
    private final EstacionRepository estacionRepository;
    private final PerfumeEstacionRepository perfumeEstacionRepository;

    // constructor con las 5 dependencias...
    public CalculadorEstacionalidad(PerfumeRepository perfumeRepository,
                                    PerfumeAcordeRepository perfumeAcordeRepository,
                                    AcordeEstacionRepository acordeEstacionRepository,
                                    EstacionRepository estacionRepository,
                                    PerfumeEstacionRepository perfumeEstacionRepository) {
        this.perfumeRepository = perfumeRepository;
        this.perfumeAcordeRepository = perfumeAcordeRepository;
        this.acordeEstacionRepository = acordeEstacionRepository;
        this.estacionRepository = estacionRepository;
        this.perfumeEstacionRepository = perfumeEstacionRepository;
    }

    // pesos de posición (Opción A): índice 0 = orden 1, ... índice 4 = orden 5
    private static final double[] PESOS_POSICION = {1.00, 0.75, 0.55, 0.35, 0.20};

    @Override
    public void run(String... args) throws Exception {
        // 1. Cargar la matriz en memoria: Map<acordeId, Map<estacionId, peso>>
        Map<Long, Map<Long, Double>> matriz = new HashMap<>();
        for (AcordeEstacion ae : acordeEstacionRepository.findAll()) {
            Long acordeId = ae.getAcorde().getId();
            Long estacionId = ae.getEstacion().getId();
            double peso = ae.getValor().doubleValue();
            matriz.computeIfAbsent(acordeId, k -> new HashMap<>()).put(estacionId, peso);
        }

        // 2. Traer las 4 estaciones
        List<Estacion> estaciones = estacionRepository.findAll();

        // 3. Por cada perfume, calcular sus 4 adecuaciones
        List<Perfume> perfumes = perfumeRepository.findAll();
        for (Perfume perfume : perfumes) {
            List<PerfumeAcorde> acordesDelPerfume = perfumeAcordeRepository.findByPerfume(perfume);
            if (acordesDelPerfume.isEmpty()) continue;

            for (Estacion estacion : estaciones) {
                double sumaPonderada = 0;
                double sumaPesos = 0;

                for (PerfumeAcorde pa : acordesDelPerfume) {
                    int orden = pa.getOrden();               // 1 a 5
                    double pesoPosicion = PESOS_POSICION[orden - 1];  // índice 0-4
                    Long acordeId = pa.getAcorde().getId();

                    // buscar el peso del acorde para esta estacion en la matriz (en memoria)
                    Double pesoMatriz = matriz.getOrDefault(acordeId, Map.of())
                            .get(estacion.getId());
                    if (pesoMatriz == null) continue;  // acorde sin peso, saltear

                    sumaPonderada += pesoMatriz * pesoPosicion;
                    sumaPesos += pesoPosicion;
                }

                if (sumaPesos == 0) continue;
                double adecuacion = sumaPonderada / sumaPesos;

                // guardar en perfume_estacion
                PerfumeEstacion pe = new PerfumeEstacion();
                pe.setPerfume(perfume);
                pe.setEstacion(estacion);
                pe.setAdecuacion(adecuacion);
                perfumeEstacionRepository.save(pe);
            }
        }
        System.out.println(">>> Estacionalidad calculada");
    }
}
