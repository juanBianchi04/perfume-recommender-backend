package com.bianchiigoa.perfume_recommender.repository;

import com.bianchiigoa.perfume_recommender.model.NotaPerfume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaPerfumeRepository extends JpaRepository<NotaPerfume,Long> {
}
