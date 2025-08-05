package virtuosi.digitales.ballatores.liter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import virtuosi.digitales.ballatores.liter.model.Autor;
import virtuosi.digitales.ballatores.liter.model.Libro;

import java.util.List;
/**
 * Repositorio JPA para la entidad Autor.
 *
 * @author HEAM
 */
public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE (a.nacimiento <= :anio AND (a.fallecimiento IS NULL OR a.fallecimiento >= :anio))")
    List<Autor> encontrarVivosEnAnio(Integer anio);
}
