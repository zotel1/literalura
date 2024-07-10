package com.alura.challenge.literalura.principal;

import com.alura.challenge.literalura.model.*;
import com.alura.challenge.literalura.repository.AutorRepository;
import com.alura.challenge.literalura.repository.LibrosRepository;
import com.alura.challenge.literalura.service.ConsumoAPI;
import com.alura.challenge.literalura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosLibros> datosLibros = new ArrayList<>();
    private LibrosRepository librosRepository;
    private AutorRepository autorRepository;
    private List<Libros> libros;
    private Optional<Libros> librosBuscados;
    private Autor datosAutor;

    public Principal(LibrosRepository librosRepository, AutorRepository autorRepository) {
        this.librosRepository = librosRepository;
        this.autorRepository = autorRepository;
    }


    public void muestraElMenu() {
        var opcion = -1;
        while (opcion !=0) {
            var menu = """
            ____________________________________________________
            1- Buscar libro por título
            2- Listar libros registrados
            3- Listar autores registrados
            4- Listar autores vivos en un determinado año
            5- Listar libros por idioma
            0- Salir
            ____________________________________________________
            
            """;
            System.out.printf(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                case 4:
                    ListarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opcion inválida");
            }
        }
        }

        private DatosLibros getDatosLibros() {
            System.out.println("Ingrese el nombre del libro que quiere buscar: ");
            String titulo = teclado.nextLine();
            String json = consumoAPI.obtenerDatos((URL_BASE + titulo.toLowerCase().replace(" ", "+")));
            return conversor.obtenerDatos(json, DatosLibros.class);

            if (datos)
        }

    private void buscarLibroPorTitulo() {
        DatosLibros datosLibros = getDatosLibros();
        
        Libros libros = new Libros(datosLibros, datosAutor);
        librosRepository.save(libros);
        // datosSeries.add(datos);
        System.out.println(datosLibros);
    }

    }


