package com.alura.challenge.literalura.repository;
import com.alura.challenge.literalura.model.Lenguajes;
import com.alura.challenge.literalura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;



public interface LibrosRepository extends JpaRepository<Libros, Long> {

    @Query("SELECT l FROM Libros l JOIN FETCH l.idiomas idiomas JOIN FETCH l.autores WHERE idiomas = :idiomas")
    List<Libros> findByIdiomas (@Param("idiomas")Lenguajes idiomas);

    @Query("SELECT l FROM Libros l LEFT JOIN FETCH l.autores LEFT JOIN FETCH l.idiomas WHERE l.id = :id")
    Optional<Libros> findByIdWithAutoresAndIdiomas(@Param("id") Long id);

    @Query("SELECT l FROM Libros l LEFT JOIN FETCH l.autores LEFT JOIN FETCH l.idiomas")
    List<Libros> findAllWithAutoresAndIdiomas();
}
