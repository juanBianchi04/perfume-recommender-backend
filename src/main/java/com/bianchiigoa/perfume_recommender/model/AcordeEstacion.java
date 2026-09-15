package com.bianchiigoa.perfume_recommender.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "acorde_estacion")
@Getter
@Setter
@NoArgsConstructor

public class AcordeEstacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name ="acorde_id")
    private Acorde acorde;

    @ManyToOne
    @JoinColumn(name = "estacion_id")
    private Estacion estacion;

}
