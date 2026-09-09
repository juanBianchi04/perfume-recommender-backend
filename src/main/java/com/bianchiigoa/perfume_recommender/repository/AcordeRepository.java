package com.bianchiigoa.perfume_recommender.repository;

import com.bianchiigoa.perfume_recommender.model.Acorde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcordeRepository extends JpaRepository<Acorde,Long> {
}
