package com.bianchiigoa.perfume_recommender.service;

import com.bianchiigoa.perfume_recommender.model.*;
import jakarta.persistence.Column;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

public interface PerfumeService {

    public Perfume crearPerfume(Marca marca, String nombre, Genero genero, Integer anioLanzamiento, BigDecimal ratingValue, Integer ratingCount);
    public List<Nota> getNotas(Perfume p);
    public List<Acorde> getAcordes(Perfume p);
}