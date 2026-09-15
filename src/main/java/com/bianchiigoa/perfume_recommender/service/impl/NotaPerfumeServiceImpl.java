package com.bianchiigoa.perfume_recommender.service.impl;

import com.bianchiigoa.perfume_recommender.model.NivelPiramide;
import com.bianchiigoa.perfume_recommender.model.Nota;
import com.bianchiigoa.perfume_recommender.model.NotaPerfume;
import com.bianchiigoa.perfume_recommender.model.Perfume;
import com.bianchiigoa.perfume_recommender.repository.NotaPerfumeRepository;
import com.bianchiigoa.perfume_recommender.service.NotaPerfumeService;
import org.springframework.stereotype.Service;

@Service
public class NotaPerfumeServiceImpl implements NotaPerfumeService {

    public final NotaPerfumeRepository notaPerfumeRepository;
    public NotaPerfumeServiceImpl(NotaPerfumeRepository notaPerfumeRepository){
        this.notaPerfumeRepository = notaPerfumeRepository;
    }

    @Override
    public void addNotaPerfume(Perfume perfume, Nota nota, NivelPiramide nivel){
        NotaPerfume nuevo = new NotaPerfume();
        nuevo.setPerfume(perfume);
        nuevo.setNota(nota);
        nuevo.setNivelPiramide(nivel);
        notaPerfumeRepository.save(nuevo);
    }

}
