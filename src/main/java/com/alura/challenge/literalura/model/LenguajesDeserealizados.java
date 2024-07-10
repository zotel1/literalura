package com.alura.challenge.literalura.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Arrays;

public class LenguajesDeserealizados extends JsonDeserializer<Lenguajes> {

    @Override
    public Lenguajes deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        String key = jsonParser.getText().toUpperCase().trim(); // Convertimos el valor a mayúsculas y quitamos los espacios del mismo
        return Arrays.stream(Lenguajes.values())
                .filter(lenguajes -> lenguajes.getLenguajes().equalsIgnoreCase(key))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No existe una constante enum en com.alura.challenge.literalura.model.Lenguajes." + key));
    }
}
