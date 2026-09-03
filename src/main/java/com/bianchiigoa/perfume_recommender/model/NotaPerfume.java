package com.bianchiigoa.perfume_recommender.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "nota_perfume" )
@Getter
@Setter
public class NotaPerfume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nota_id")
    private Nota nota;

    @ManyToOne
    @JoinColumn(name = "perfume_id")
    private Perfume perfume;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_piramide")
    private NivelPiramide nivelPiramide;

}
