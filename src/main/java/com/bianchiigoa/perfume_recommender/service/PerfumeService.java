package com.bianchiigoa.perfume_recommender.service;

import com.bianchiigoa.perfume_recommender.model.Genero;
import com.bianchiigoa.perfume_recommender.model.Marca;
import com.bianchiigoa.perfume_recommender.model.Perfume;
import jakarta.persistence.Column;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public interface PerfumeService {

    public Perfume crearPerfume(Marca marca, String nombre, Genero genero, Integer anioLanzamiento, BigDecimal ratingValue, Integer ratingCount);
}