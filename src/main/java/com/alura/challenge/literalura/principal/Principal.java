package com.alura.challenge.literalura.principal;

import com.alura.challenge.literalura.model.DatosLibros;
import com.alura.challenge.literalura.repository.LibrosRepository;
import com.alura.challenge.literalura.service.ConsumoAPI;
import com.alura.challenge.literalura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "gutendex.com/books";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosLibros> datosLibros = new ArrayList<>();
    private LibrosRepository repositorio;


    public String menu = """
            ____________________________________________________
            1- Buscar libro por título
            2- Listar libros registrados
            3- Listar autores registrados
            4- Listar autores vivos en un determinado año
            5- Listar libros por idioma
            0- Salir
            ____________________________________________________
            
            """;
}
