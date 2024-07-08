package com.alura.challenge.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer vive;
    private Integer noVive;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libros> libros;

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.vive = datosAutor.vive();
        this.noVive = datosAutor.noVive();
    }

    public Autor() {}

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

    public List<Libros> getLibros() {
        return libros;
    }

    public void setLibros(List<Libros> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nombre='" + nombre + '\'' +
                ", vive=" + vive +
                ", noVive=" + noVive +
                ", libros=" + libros +
                '}';
    }
}
