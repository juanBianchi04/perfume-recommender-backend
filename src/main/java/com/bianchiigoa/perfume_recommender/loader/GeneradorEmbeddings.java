package com.bianchiigoa.perfume_recommender.loader;

import com.bianchiigoa.perfume_recommender.model.Acorde;
import com.bianchiigoa.perfume_recommender.model.Nota;
import com.bianchiigoa.perfume_recommender.model.Perfume;
import com.bianchiigoa.perfume_recommender.repository.PerfumeRepository;
import com.bianchiigoa.perfume_recommender.service.PerfumeService;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GeneradorEmbeddings implements CommandLineRunner {

    private final PerfumeRepository perfumeRepository;
    private final PerfumeService perfumeService;
    private final EmbeddingModel embeddingModel;
    private final JdbcTemplate jdbcTemplate;

    public GeneradorEmbeddings(PerfumeRepository perfumeRepository,
                               PerfumeService perfumeService,
                               EmbeddingModel embeddingModel,
                               JdbcTemplate jdbcTemplate) {
        this.perfumeRepository = perfumeRepository;
        this.perfumeService = perfumeService;
        this.embeddingModel = embeddingModel;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        // PRUEBA: solo los primeros 10
        List<Perfume> perfumes = perfumeRepository.findSinEmbedding();

        for (Perfume p : perfumes) {
            String texto = armarTexto(p);
            System.out.println("Texto: " + texto);

            // generar el embedding con OpenAI
            float[] embedding = embeddingModel.embed(texto);

            // guardar en la columna embedding
            jdbcTemplate.update(
                    "UPDATE perfume SET embedding = ?::vector WHERE id = ?",
                    vectorToString(embedding), p.getId()
            );

            System.out.println(">>> Embebido perfume " + p.getId());
        }
        System.out.println(">>> Listo: " + perfumes.size() + " embeddings generados");
    }

    private String armarTexto(Perfume p) {
        List<Nota> notas = perfumeService.getNotas(p);
        List<Acorde> acordes = perfumeService.getAcordes(p);

        String nombreNotas = notas.stream()
                .map(Nota::getNombre)
                .collect(Collectors.joining(", "));

        String nombreAcordes = acordes.stream()
                .map(Acorde::getNombre)
                .collect(Collectors.joining(", "));

        return "Notas: " + nombreNotas + ". Acordes: " + nombreAcordes + ".";
    }

    // convierte el float[] al formato de pgvector: [0.1,0.2,...]
    private String vectorToString(float[] v) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < v.length; i++) {
            if (i > 0) sb.append(",");
            sb.append(v[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}

