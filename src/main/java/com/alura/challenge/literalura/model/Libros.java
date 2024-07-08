package com.alura.challenge.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libros {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String titulo;
    private String idiomas;
    private double dowloadsCount;

    @ManyToMany
    private Autor autor;

    private String asignaturas;


    public Libros() {}

    public Libros(DatosLibros datosLibros, Autor dataAutor) {
        this.titulo = datosLibros.titulo();
        this.autor = dataAutor;
        this.idiomas = datosLibros.idiomas().get(0);
        this.dowloadsCount = datosLibros.downloadCount();
    }

}
