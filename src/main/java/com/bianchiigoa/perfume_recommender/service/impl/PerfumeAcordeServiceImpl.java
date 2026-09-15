package com.bianchiigoa.perfume_recommender.service.impl;

import com.bianchiigoa.perfume_recommender.model.Acorde;
import com.bianchiigoa.perfume_recommender.model.Perfume;
import com.bianchiigoa.perfume_recommender.model.PerfumeAcorde;
import com.bianchiigoa.perfume_recommender.repository.PerfumeAcordeRepository;
import com.bianchiigoa.perfume_recommender.service.PerfumeAcordeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfumeAcordeServiceImpl implements PerfumeAcordeService {

    @Autowired
    private PerfumeAcordeRepository perfumeAcordeRepository;

    @Override
    public void addPerfumeAcorde(int orden, Perfume perfume, Acorde acorde){
        PerfumeAcorde perfumeAcorde = new PerfumeAcorde(orden,perfume,acorde);
        perfumeAcordeRepository.save(perfumeAcorde);
    }
}
