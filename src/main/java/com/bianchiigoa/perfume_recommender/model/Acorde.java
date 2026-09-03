package com.bianchiigoa.perfume_recommender.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "acorde")
@Getter
@Setter

public class Acorde {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

}
