package com.alura.challenge.literalura.principal;

import com.alura.challenge.literalura.model.DatosLibros;
import com.alura.challenge.literalura.model.Libros;
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
    private final String URL_BASE = "gutendex.com/books";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosLibros> datosLibros = new ArrayList<>();
    private LibrosRepository repositorio;
    private List<Libros> libros;
    private Optional<Libros> librosBuscados;

    public Principal(LibrosRepository repositoriy) {
        this.repositorio = repositoriy;
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
                //    listarLibrosRegistrados();
                  //  break;
        //        case 3:
          //          listarAutoresRegistrados();
            //    case 4:
              //      ListarAutoresVivos();
                //    break;
 //               case 5:
   //                 listarLibrosPorIdioma();
     //               break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                default:
                    System.out.println("Opcion inválida");
            }
        }
        }

        public void buscarLibroPorTitulo() {
            System.out.println("Escribe el nombre del libro que deseas buscar");
            var nombreLibro = teclado.nextLine();
            librosBuscados = repositorio.findByTituloContainsIgnoreCase(nombreLibro);

            if (librosBuscados.isPresent()) {
                System.out.println("El libro buscado es: " + librosBuscados.get());
            } else {
                System.out.println("Libro no encontrado.");
            }

        }

}
