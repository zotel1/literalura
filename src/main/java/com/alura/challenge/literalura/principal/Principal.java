package com.alura.challenge.literalura.principal;

import com.alura.challenge.literalura.model.Autor;
import com.alura.challenge.literalura.model.Datos;
import com.alura.challenge.literalura.model.DatosLibros;
import com.alura.challenge.literalura.model.Lenguajes;
import com.alura.challenge.literalura.model.Libros;
import com.alura.challenge.literalura.repository.AutorRepository;
import com.alura.challenge.literalura.repository.LibrosRepository;
import com.alura.challenge.literalura.service.ConsumoAPI;
import com.alura.challenge.literalura.service.ConvierteDatos;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private Set<Libros> librosAPI = new HashSet<>();
    private LibrosRepository librosRepository;
    private AutorRepository autorRepository;
    private Set<Libros> libros;

    @Autowired
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
                    listarAutoresVivos();
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
            var titulo = teclado.nextLine();
            var json = consumoAPI.obtenerDatos(URL_BASE + titulo.toLowerCase().replace(" ", "+"));
            Datos datos = conversor.obtenerDatos(json, Datos.class);

            if (datos.resultados().isEmpty()) {
                throw new RuntimeException("No se encontraron libros con ese nombre.");
            }

            DatosLibros datosLibros = datos.resultados().get(0); // Obtiene el primer libro de los resultados
            return new DatosLibros(
                    datosLibros.id(),
                    datosLibros.titulo(),
                    datosLibros.autor(),
                    datosLibros.idiomas(),
                    datosLibros.cantidadDescargas()
            );
            }

    private void buscarLibroPorTitulo() {
        DatosLibros datosLibros = getDatosLibros();
        Set<Autor> autor = datosLibros.autor().stream()
                .map(datosAutor -> new Autor(datosAutor.nombre(), datosAutor.vive(),datosAutor.noVive()))
                .collect(Collectors.toSet());
        Libros libros = new Libros(datosLibros.titulo(), autor, datosLibros.cantidadDescargas(), new HashSet<>(datosLibros.idiomas()));
        librosRepository.save(libros);
        librosAPI.add(libros);
        System.out.println(libros.toString());
    }

    @Transactional(readOnly = true) // Esta anotación nos es útil cuando queremos asegurarnos de que un método no realice modificaciones en la base de datos y solo realice consultas
    private void listarLibrosRegistrados() {
        libros = new HashSet<>(librosRepository.findAll());

        libros.forEach(libro -> {
            librosRepository.findByIdWithAutoresAndIdiomas(libro.getId()).ifPresent(librosConAutores -> {
                libro.setAutores(librosConAutores.getAutores());
                libro.setIdiomas(librosConAutores.getIdiomas());
            });
        });

        // Comparador
        Comparator<Libros> comparador = Comparator.comparing(libro ->
                libro.getAutores().isEmpty() ? "" : libro.getAutores().iterator().next().getNombre());
        libros.stream()
                .sorted(comparador)
                .forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAllWithLibros();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            autores.forEach(autor -> {
                System.out.println("Autor: " + autor.getNombre());
                System.out.println("Fecha de Nacimiento: " + autor.getVive());
                System.out.println("Fecha de Fallecimiento: " + (autor.getNoVive() != null ? autor.getNoVive() : "N/A"));
                if (autor.getLibros() != null) {
                    System.out.println("Libro: " + autor.getLibros().getTitulo());
                } else {
                    System.out.println("Libro: N/A");
                }
                System.out.println();
            });
        }
    }

    @Transactional(readOnly = true)
    private void listarAutoresVivos() {
        System.out.println("Por favor escribe el año que desees buscar: ");
        var anio = teclado.nextInt();
        teclado.nextLine();
        List<Autor> autores = autorRepository.findByviveLessThanEqualAndnoViveGreaterThanEqual(anio);
        if (autores.isEmpty()) {
            System.out.println("No se pudo encontrar a un autor que haya vivido en el año: " + anio);
        } else {
            autores.forEach(autor -> {
                System.out.println("Nombre: " + autor.getNombre());
                System.out.println("Fecha de Nacimiento: " + autor.getVive());
                System.out.println("Fecha de Fallecimiento: " + (autor.getNoVive() != null ? autor.getNoVive() : "N/A"));
                System.out.println("libro: " + (autor.getLibros() != null ? autor.getLibros().getTitulo() : "N/A"));
            });
        }
    }

    @Transactional(readOnly = true)
    private void listarLibrosPorIdioma() {
        System.out.println("Por favor ingrese el idioma en el cual desea buscar los libros (es, en fr, pt, etc.)");
        var lenguajeIngresado = teclado.nextLine().toLowerCase();

        try {
            Lenguajes lenguajes = Lenguajes.fromString(lenguajeIngresado);
            List<Libros> libros = librosRepository.findByIdiomas(lenguajes);

            if (libros.isEmpty()) {
                System.out.println("No se encontraron libros en el idioma: " + lenguajeIngresado );
            } else {
                libros.forEach(libro -> {
                    Hibernate.initialize(libro.getAutores());
                    Hibernate.initialize(libro.getIdiomas());

                    System.out.println(
                            "########### Libro ##########" +
                                    "\nTitulo: " + libro.getTitulo() +
                                    "\nAutores: " + libro.getAutores().stream().map(Autor::getNombre).collect(Collectors.joining(", ")) +
                                    "\nIdiomas: " + libro.getIdiomas().stream().map(Lenguajes::getDescripcion).collect(Collectors.joining(", ")) +
                                    "\nNúmero de descargas: " + libro.getCantidadDescargas() +
                                    "\n#################### "
                    );
                });
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Idioma no reconocido: " + lenguajeIngresado);
        }
    }

}