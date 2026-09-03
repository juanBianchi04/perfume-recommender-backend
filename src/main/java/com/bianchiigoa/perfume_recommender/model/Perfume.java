package com.bianchiigoa.perfume_recommender.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "perfume")
@Getter
@Setter

public class Perfume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String url;
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @Column(name = "anio_lanzamiento")
    private Integer aniolanzamiento;

    @Column(name = "rating_value")
    private  Double ratingValue;

    @Column(name = "rating_count")
    private Integer ratingCount;

    @ManyToMany
    @JoinTable(
            name = "perfume_acorde",
            joinColumns = @JoinColumn(name = "perfume_id"),
            inverseJoinColumns = @JoinColumn(name = "acorde_id")
    )
    private List<Acorde> acordes;

    @OneToMany(mappedBy = "perfume")
    private List<NotaPerfume> notas;


}
