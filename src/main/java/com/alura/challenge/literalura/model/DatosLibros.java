package com.alura.challenge.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("id") Integer id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<Autores> autores,
        @JsonAlias("translators") List<Traducir> traducir,
        @JsonAlias("subjects") List <Asignaturas> asignaturas,
        @JsonAlias("bookshelves") List <Estanteria> estanteria,
        @JsonAlias("languages") List <Idiomas> idiomas,
        @JsonAlias("copyright") boolean copyright,
        @JsonAlias("media_type") String mediaType,
        @JsonAlias("download_count") int downloadCount
        ) {
}
