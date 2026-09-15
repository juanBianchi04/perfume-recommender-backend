package com.bianchiigoa.perfume_recommender.service;

import com.bianchiigoa.perfume_recommender.model.Acorde;
import com.bianchiigoa.perfume_recommender.model.Perfume;
import org.springframework.stereotype.Service;

public interface PerfumeAcordeService {
    public void addPerfumeAcorde(int orden, Perfume perfume, Acorde acorde);
}
