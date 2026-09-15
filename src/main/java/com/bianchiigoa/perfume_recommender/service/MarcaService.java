package com.bianchiigoa.perfume_recommender.service;

import com.bianchiigoa.perfume_recommender.model.Marca;
import org.springframework.stereotype.Service;


public interface MarcaService {
  public Marca buscarOCrearMarca(String nombre);

}
