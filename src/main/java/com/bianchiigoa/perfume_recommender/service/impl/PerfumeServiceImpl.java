package com.bianchiigoa.perfume_recommender.service.impl;

import com.bianchiigoa.perfume_recommender.model.*;
import com.bianchiigoa.perfume_recommender.repository.NotaPerfumeRepository;
import com.bianchiigoa.perfume_recommender.repository.PerfumeAcordeRepository;
import com.bianchiigoa.perfume_recommender.repository.PerfumeRepository;
import com.bianchiigoa.perfume_recommender.service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PerfumeServiceImpl implements PerfumeService {

    private final PerfumeRepository perfumeRepository;
    private final NotaPerfumeRepository notaPerfumeRepository;
    private final PerfumeAcordeRepository perfumeAcordeRepository;

    public PerfumeServiceImpl(PerfumeRepository perfumeRepository, NotaPerfumeRepository notaPerfumeRepository, PerfumeAcordeRepository perfumeAcordeRepository){
     this.perfumeRepository = perfumeRepository;
     this.notaPerfumeRepository = notaPerfumeRepository;
     this.perfumeAcordeRepository = perfumeAcordeRepository;
    }


    @Override
    public Perfume crearPerfume(Marca marca, String nombre, Genero genero, Integer anioLanzamiento, BigDecimal ratingValue, Integer ratingCount){
     Perfume nuevo =  new Perfume(marca,nombre,genero, anioLanzamiento, ratingValue,ratingCount);
     return perfumeRepository.save(nuevo);
    }

    @Override
    public List<Nota> getNotas(Perfume p){
     return notaPerfumeRepository.findNotasByPerfume(p);
    }

    @Override
    public List<Acorde> getAcordes(Perfume p){
      return perfumeAcordeRepository.findByPerfume(p).stream()
              .map(PerfumeAcorde::getAcorde)
              .toList();
    }
}
