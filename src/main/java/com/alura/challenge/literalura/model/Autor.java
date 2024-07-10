package com.alura.challenge.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;
    private Integer vive;
    private Integer noVive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "libro_id")
    private Libros libros;

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.vive = datosAutor.vive();
        this.noVive = datosAutor.noVive();
    }

    public Autor() {}

    public Autor(String nombre, Integer vive, Integer noVive) {
        this.nombre = nombre;
        this.vive = vive;
        this.noVive = noVive;
    }

    @Override
    public String toString() {
        return "Autor{" + nombre + '\'' +
                ", El autor nacio en: " + vive +
                ", El autor fallecío en: " + noVive +
                ", libros=" + libros +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getVive() {
        return vive;
    }

    public void setVive(Integer vive) {
        this.vive = vive;
    }

    public Integer getNoVive() {
        return noVive;
    }

    public void setNoVive(Integer noVive) {
        this.noVive = noVive;
    }

    public Libros getLibros() {
        return libros;
    }

    public void setLibros(Libros libros) {
        this.libros = libros;
    }
}
