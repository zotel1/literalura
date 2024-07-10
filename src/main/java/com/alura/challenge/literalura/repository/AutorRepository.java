package com.alura.challenge.literalura.repository;

import com.alura.challenge.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombreIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.libros")
    List<Autor> findAllWithLibros();


    @Query("SELECT a FROM Autor a WHERE LOWER(a.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Autor> buscarAutorPorNombre(@Param("nombre") String nombre);

    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.libros WHERE a.vive <= :anio AND (a.noVive >= :anio OR a.noVive IS NULL)")
    List<Autor> findByviveLessThanEqualAndnoViveGreaterThanEqual(@Param("anio") int anio);


}

