package virtuosi.digitales.ballatores.liter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO para representar un autor recibido desde la API de Gutendex.
 * Ignora propiedades desconocidas del JSON.
 *
 * @author HEAM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorDTO(
        String name,
        Integer birth_year,
        Integer death_year
) {}
