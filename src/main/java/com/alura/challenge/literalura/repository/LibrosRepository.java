package com.alura.challenge.literalura.repository;
import com.alura.challenge.literalura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibrosRepository extends JpaRepository <Libros, Long> {

    Optional<Libros> findByTituloContainsIgnoreCase(String nombreLibro);

}
