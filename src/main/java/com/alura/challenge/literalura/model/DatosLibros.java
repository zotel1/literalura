package com.alura.challenge.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("id") Integer id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<Autor> autor,

        @JsonAlias("subjects") List <Asignaturas> asignaturas,
        @JsonAlias("bookshelves") List <Estanteria> estanteria,
        @JsonAlias("languages") List <String> idiomas,
        @JsonAlias("copyright") boolean copyright,
        @JsonAlias("media_type") String mediaType,
        Map<String, Format> Formats,
        @JsonAlias("download_count") int downloadCount
        ) {
}
