package com.bianchiigoa.perfume_recommender.service.impl;

import com.bianchiigoa.perfume_recommender.model.Genero;
import com.bianchiigoa.perfume_recommender.model.Marca;
import com.bianchiigoa.perfume_recommender.model.Perfume;
import com.bianchiigoa.perfume_recommender.repository.PerfumeRepository;
import com.bianchiigoa.perfume_recommender.service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PerfumeServiceImpl implements PerfumeService {

    private final PerfumeRepository perfumeRepository;
    public PerfumeServiceImpl(PerfumeRepository perfumeRepository){
     this.perfumeRepository = perfumeRepository;
    }

    @Override
    public Perfume crearPerfume(Marca marca, String nombre, Genero genero, Integer anioLanzamiento, BigDecimal ratingValue, Integer ratingCount){
     Perfume nuevo =  new Perfume(marca,nombre,genero, anioLanzamiento, ratingValue,ratingCount);
     return perfumeRepository.save(nuevo);
    }
}
