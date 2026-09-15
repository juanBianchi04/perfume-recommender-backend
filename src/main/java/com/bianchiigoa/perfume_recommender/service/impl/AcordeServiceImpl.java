package com.bianchiigoa.perfume_recommender.service.impl;

import com.bianchiigoa.perfume_recommender.model.Acorde;
import com.bianchiigoa.perfume_recommender.repository.AcordeRepository;
import com.bianchiigoa.perfume_recommender.service.AcordeService;
import org.springframework.stereotype.Service;

@Service
public class AcordeServiceImpl implements AcordeService {

    private final AcordeRepository acordeRepository;
    public AcordeServiceImpl(AcordeRepository acordeRepository){
      this.acordeRepository = acordeRepository;
    }

    @Override
    public Acorde buscarOCrearAcorde(String nombre){
        return acordeRepository.findByNombre(nombre)
                .orElseGet(() ->{
                    Acorde nuevo = new Acorde();
                    nuevo.setNombre(nombre);
                    return acordeRepository.save(nuevo);
                });
    }

}
