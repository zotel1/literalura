package com.alura.challenge.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "libros")
public class Libros {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @OneToMany(mappedBy = "libros", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Autor> autores;

    private Double cantidadDescargas;

    @ElementCollection(targetClass = Lenguajes.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "idiomas", joinColumns = @JoinColumn(name = "libro_id"))
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "idiomas")
    private Set<Lenguajes> idiomas;

    public Libros() {}



    public Libros(String titulo, Set<Autor> autores, Double cantidadDescargas, Set<Lenguajes> idiomas) {
        this.titulo = titulo;
        this.autores = autores;
        this.cantidadDescargas = cantidadDescargas;
        this.idiomas = idiomas;


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Set<Autor> getAutores() {
        return autores;
    }

    public void setAutores(Set<Autor> autores) {
        this.autores = autores;
    }

    public Double getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(Double cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    public Set<Lenguajes> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Set<Lenguajes> idiomas) {
        this.idiomas = idiomas;
    }

    @Override
    public String toString() {
        return "Libros{" +
                "titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", cantidadDescargas=" + cantidadDescargas +
                ", idiomas=" + idiomas +
                '}';
    }
}
