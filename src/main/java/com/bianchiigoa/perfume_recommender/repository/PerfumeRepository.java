package com.bianchiigoa.perfume_recommender.repository;

import com.bianchiigoa.perfume_recommender.model.Nota;
import com.bianchiigoa.perfume_recommender.model.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PerfumeRepository extends JpaRepository<Perfume,Long> {
    @Query(value = "SELECT * FROM perfume WHERE embedding IS NULL", nativeQuery = true)
    List<Perfume> findSinEmbedding();
}
