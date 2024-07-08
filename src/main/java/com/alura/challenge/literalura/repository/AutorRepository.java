package com.alura.challenge.literalura.repository;

import com.alura.challenge.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNombreIgnoreCase(String nombre);

    @Query("Select a FROM Autor a WHERE :year BETWEEN a.vive AND a.noVive")
    List<Autor> autoresLiveForYear(Integer year);
}
