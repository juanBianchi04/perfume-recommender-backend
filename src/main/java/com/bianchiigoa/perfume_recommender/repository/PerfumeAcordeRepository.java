package com.bianchiigoa.perfume_recommender.repository;

import com.bianchiigoa.perfume_recommender.model.Acorde;
import com.bianchiigoa.perfume_recommender.model.Perfume;
import com.bianchiigoa.perfume_recommender.model.PerfumeAcorde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PerfumeAcordeRepository extends JpaRepository<PerfumeAcorde,Long> {
    List<PerfumeAcorde> findByPerfume(Perfume perfume);
}
