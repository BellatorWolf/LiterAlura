package virtuosi.digitales.ballatores.liter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import virtuosi.digitales.ballatores.liter.model.Autor;
import virtuosi.digitales.ballatores.liter.model.Libro;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Libro.
 *
 * @author HEAM
 */
public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByIdiomaIgnoreCase(String idioma);
    Libro findFirstByTituloContainingIgnoreCase(String titulo);
    Optional<Libro> findByTituloIgnoreCase(String titulo);

}
