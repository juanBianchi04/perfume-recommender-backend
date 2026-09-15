package com.bianchiigoa.perfume_recommender.service.impl;

import com.bianchiigoa.perfume_recommender.model.Nota;
import com.bianchiigoa.perfume_recommender.repository.NotaRepository;
import com.bianchiigoa.perfume_recommender.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotaServiceImpl implements NotaService {

    @Autowired
    private NotaRepository notaRepository;

    @Override
    public Nota buscarOCrearNota(String nombre){
        return notaRepository.findByNombre(nombre)
                .orElseGet(() -> {
                    Nota nuevo = new Nota(nombre);
                    return notaRepository.save(nuevo);
                });
    }
}
