package com.alura.challenge.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Traducir(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer nacimiento,
        @JsonAlias("death_year") Integer fallecimiento
) {
}
