package com.bianchiigoa.perfume_recommender.repository;

import com.bianchiigoa.perfume_recommender.model.Nota;
import com.bianchiigoa.perfume_recommender.model.NotaPerfume;
import com.bianchiigoa.perfume_recommender.model.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotaPerfumeRepository extends JpaRepository<NotaPerfume,Long> {
    @Query("SELECT np.nota FROM NotaPerfume np WHERE np.perfume = :perfume")
    List<Nota> findNotasByPerfume(@Param("perfume") Perfume perfume);
}
