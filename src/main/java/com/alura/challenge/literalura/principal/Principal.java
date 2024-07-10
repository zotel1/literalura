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
import org.springframework.beans.factory.annotation.Autowired;


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
              //  case 2:
//                    listarLibrosRegistrados();
  //                  break;
    //            case 3:
      //              listarAutoresRegistrados();
        //        case 4:
          //          ListarAutoresVivos();
            //        break;
              //  case 5:
                //    listarLibrosPorIdioma();
                  //  break;
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

    }


