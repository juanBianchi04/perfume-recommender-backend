package com.bianchiigoa.perfume_recommender.repository;

import com.bianchiigoa.perfume_recommender.model.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfumeRepository extends JpaRepository<Perfume,Long> {
}
