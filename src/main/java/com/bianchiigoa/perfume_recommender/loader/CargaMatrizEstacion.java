package com.bianchiigoa.perfume_recommender.loader;

import com.bianchiigoa.perfume_recommender.model.Acorde;
import com.bianchiigoa.perfume_recommender.model.AcordeEstacion;
import com.bianchiigoa.perfume_recommender.model.Estacion;
import com.bianchiigoa.perfume_recommender.model.NombreEstacion;
import com.bianchiigoa.perfume_recommender.repository.AcordeEstacionRepository;
import com.bianchiigoa.perfume_recommender.repository.AcordeRepository;
import com.bianchiigoa.perfume_recommender.repository.EstacionRepository;
import com.opencsv.CSVReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;

//@Component
    public class CargaMatrizEstacion implements CommandLineRunner {

        private final AcordeRepository acordeRepository;
        private final EstacionRepository estacionRepository;
        private final AcordeEstacionRepository acordeEstacionRepository;

        public CargaMatrizEstacion(AcordeRepository acordeRepository,
                                   EstacionRepository estacionRepository,
                                   AcordeEstacionRepository acordeEstacionRepository) {
            this.acordeRepository = acordeRepository;
            this.estacionRepository = estacionRepository;
            this.acordeEstacionRepository = acordeEstacionRepository;
        }

        @Override
        public void run(String... args) throws Exception {
            ClassPathResource recurso = new ClassPathResource("matriz-acorde-estacion.csv");
            Reader reader = new InputStreamReader(recurso.getInputStream());
            CSVReader csvReader = new CSVReader(reader);

            // las 4 estaciones, en el orden de las columnas del CSV
            NombreEstacion[] estaciones = {
                    NombreEstacion.invierno, NombreEstacion.otonio,
                    NombreEstacion.primavera, NombreEstacion.verano
            };

            String[] fila;
            boolean primera = true;
            while ((fila = csvReader.readNext()) != null) {
                if (primera) { primera = false; continue; }  // saltear encabezado

                String nombreAcorde = fila[0].trim();
                Acorde acorde = acordeRepository.findByNombre(nombreAcorde).orElse(null);
                if (acorde == null) {
                    System.out.println("Acorde no encontrado: " + nombreAcorde);
                    continue;  // si el acorde no está en la base, lo saltea
                }

                // por cada estación (columnas 1 a 4)
                for (int i = 0; i < 4; i++) {
                    Estacion estacion = estacionRepository.findByNombre(estaciones[i]).orElse(null);
                    if (estacion == null) continue;

                    BigDecimal peso = new BigDecimal(fila[i + 1].trim());

                    AcordeEstacion ae = new AcordeEstacion();
                    ae.setAcorde(acorde);
                    ae.setEstacion(estacion);
                    ae.setValor(peso);   // o setPeso, según cómo lo llamaste
                    acordeEstacionRepository.save(ae);
                }
            }
            csvReader.close();
            System.out.println(">>> Matriz cargada");
        }
    }

