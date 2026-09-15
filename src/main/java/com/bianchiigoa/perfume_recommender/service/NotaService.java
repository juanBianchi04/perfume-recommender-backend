package com.bianchiigoa.perfume_recommender.service;

import com.bianchiigoa.perfume_recommender.model.Nota;
import org.springframework.stereotype.Service;

public interface NotaService {
    public Nota buscarOCrearNota(String nombre);
}
