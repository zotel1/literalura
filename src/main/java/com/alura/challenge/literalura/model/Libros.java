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

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libros() {}



    public Libros(DatosLibros datosLibros, Autor dataAutor) {
        this.titulo = datosLibros.titulo();
        this.autor = dataAutor;
        this.idiomas = datosLibros.idiomas().get(0);
        this.dowloadsCount = datosLibros.downloadCount();
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public double getDowloadsCount() {
        return dowloadsCount;
    }

    public void setDowloadsCount(double dowloadsCount) {
        this.dowloadsCount = dowloadsCount;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libros{" +
                "titulo='" + titulo + '\'' +
                ", idiomas='" + idiomas + '\'' +
                ", dowloadsCount=" + dowloadsCount +
                ", autor=" + autor +
                '}';
    }
}
