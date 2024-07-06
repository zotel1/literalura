package com.alura.challenge.literalura.model;

public class Libros {
    private Integer id;
    private String titulo;
    private String autores;
    private String traducir;
    private String asignaturas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getTraducir() {
        return traducir;
    }

    public void setTraducir(String traducir) {
        this.traducir = traducir;
    }

    public String getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(String asignaturas) {
        this.asignaturas = asignaturas;
    }
}
