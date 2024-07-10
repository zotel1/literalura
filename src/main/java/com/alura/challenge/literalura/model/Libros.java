package com.alura.challenge.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libros {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String titulo;
    private String idiomas;
    private double cantidadDescargas;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libros() {}



    public Libros(DatosLibros datosLibros, Autor dataAutor) {
        // Verificación del título
        String tituloLibro = datosLibros.titulo();
        if (tituloLibro == null || tituloLibro.isEmpty()) {
            throw new IllegalArgumentException("El título del libro no puede estar vacío.");
            // Puedes manejar esto según tu lógica de negocio
            // Por ejemplo, asignando un valor predeterminado o lanzando una excepción personalizada
        }
        this.titulo = datosLibros.titulo();
        this.autor = dataAutor;
        List<String> idiomasList = datosLibros.idiomas();
        if (idiomasList != null && !idiomasList.isEmpty()) {
            this.idiomas = idiomasList.get(0);
        } else {
            this.idiomas = ""; // O establece un valor predeterminado apropiado
        }
        this.cantidadDescargas = datosLibros.cantidadDescargas();
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

    public double getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(double cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
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
                ", cantidadDescargas=" + cantidadDescargas +
                ", autor=" + autor +
                '}';
    }
}
