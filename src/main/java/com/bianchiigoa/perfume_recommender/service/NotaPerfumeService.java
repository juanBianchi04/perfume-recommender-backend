package com.bianchiigoa.perfume_recommender.service;

import com.bianchiigoa.perfume_recommender.model.NivelPiramide;
import com.bianchiigoa.perfume_recommender.model.Nota;
import com.bianchiigoa.perfume_recommender.model.Perfume;

public interface NotaPerfumeService {
    public void addNotaPerfume(Perfume perfume, Nota nota, NivelPiramide nivel);
}
