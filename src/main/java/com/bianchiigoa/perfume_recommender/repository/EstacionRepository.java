package com.bianchiigoa.perfume_recommender.repository;

import com.bianchiigoa.perfume_recommender.model.Estacion;
import com.bianchiigoa.perfume_recommender.model.NombreEstacion;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion,Long> {
    Optional<Estacion> findByNombre(NombreEstacion nombre);
}
