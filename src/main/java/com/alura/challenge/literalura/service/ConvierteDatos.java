package com.alura.challenge.literalura.service;

import com.alura.challenge.literalura.model.Lenguajes;
import com.alura.challenge.literalura.model.LenguajesDeserealizados;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class ConvierteDatos implements IConvierteDatos {
    private ObjectMapper objectMapper = new ObjectMapper();
    public ConvierteDatos() {
        objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Lenguajes.class, new LenguajesDeserealizados());
        objectMapper.registerModule(module);
    }

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        if (json == null || json.trim().isEmpty()) {
            throw new RuntimeException("La respuesta JSON está vacía");
        }
        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al procesar JSON", e);
        }
    }
}

