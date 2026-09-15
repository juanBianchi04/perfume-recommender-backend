package com.bianchiigoa.perfume_recommender.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "perfume")
@Getter
@Setter
@NoArgsConstructor
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
    private BigDecimal ratingValue;

    @Column(name = "rating_count")
    private Integer ratingCount;

    @OneToMany(mappedBy = "perfume")
    private List<PerfumeAcorde> acordes;

    @OneToMany(mappedBy = "perfume")
    private List<NotaPerfume> notas;

    @OneToMany(mappedBy = "perfume")
    private List<PerfumeEstacion> estaciones;


    public Perfume(Marca marca, String nombre, Genero genero, Integer anioLanzamiento, BigDecimal ratingValue, Integer ratingCount){
      this.marca = marca;
      this.nombre = nombre;
      this.genero = genero;
      this.aniolanzamiento = anioLanzamiento;
      this.ratingValue = ratingValue;
      this.ratingCount = ratingCount;
    }
}
