package com.bianchiigoa.perfume_recommender.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "marca")
@Getter
@Setter

public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private Categoria categoria;
}
