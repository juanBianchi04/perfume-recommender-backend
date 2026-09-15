package com.bianchiigoa.perfume_recommender.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "perfume_acorde")
@Getter
@Setter
@NoArgsConstructor
public class PerfumeAcorde {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer orden;

    @ManyToOne
    @JoinColumn(name = "perfume_id")
    private Perfume perfume;

    @ManyToOne
    @JoinColumn(name = "acorde_id")
    private Acorde acorde;

    public PerfumeAcorde(int orden, Perfume perfume, Acorde acorde){
      this.orden = orden;
      this.perfume = perfume;
      this.acorde = acorde;
    }
}
