package com.bianchiigoa.perfume_recommender.service.impl;

import com.bianchiigoa.perfume_recommender.model.Marca;
import com.bianchiigoa.perfume_recommender.repository.MarcaRepository;
import com.bianchiigoa.perfume_recommender.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public Marca buscarOCrearMarca(String nombre){
      return marcaRepository.findByNombre(nombre)
              .orElseGet(() -> {
                  Marca nueva = new Marca();
                  nueva.setNombre(nombre);
                  return marcaRepository.save(nueva);
              });
    }

}
