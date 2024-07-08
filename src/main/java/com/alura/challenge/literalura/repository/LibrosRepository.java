package com.alura.challenge.literalura.repository;
import com.alura.challenge.literalura.model.Libros;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibrosRepository extends JpaRepository<Libros, Long> {

    Libros findByTituloContainsIgnoreCase(String titulo);

    @Query("SELECT l FROM Libros l WHERE l.idiomas = :idiomas")
    List<Libros> findByIdiomas (String idiomas);

   // @Query("SELECT l FROM Libros l ORDER BY l.downloadsCount DESC")
   // List<Libros> topDownloads(Pageable pageable);

}
