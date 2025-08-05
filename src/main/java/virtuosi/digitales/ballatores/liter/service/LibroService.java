package virtuosi.digitales.ballatores.liter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import virtuosi.digitales.ballatores.liter.dto.AutorDTO;
import virtuosi.digitales.ballatores.liter.dto.LibroDTO;
import virtuosi.digitales.ballatores.liter.dto.ResultadoLibrosDTO;
import virtuosi.digitales.ballatores.liter.model.Autor;
import virtuosi.digitales.ballatores.liter.model.Libro;
import virtuosi.digitales.ballatores.liter.repository.AutorRepository;
import virtuosi.digitales.ballatores.liter.repository.LibroRepository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

/**
 * Servicio para manejar lógica de negocio de libros y autores.
 *
 * @author HEAM
 */
@Service
public class LibroService {

    private static final String API_URL = "https://gutendex.com/books/?search=";

    @Autowired
    private LibroRepository libroRepo;
    @Autowired
    private AutorRepository autorRepo;

    private final ObjectMapper mapper = new ObjectMapper();

    public void buscarYGuardarLibro(String tituloBuscado) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + tituloBuscado.replace(" ", "+")))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ResultadoLibrosDTO resultado = mapper.readValue(response.body(), ResultadoLibrosDTO.class);

            if (resultado.getResults().isEmpty()) {
                System.out.println("No se encontraron libros con ese título.");
                return;
            }
            LibroDTO libroDTO = resultado.getResults().getFirst();
            Libro libro = new Libro();
            libro.setTitulo(libroDTO.getTitulo());
            libro.setIdioma(libroDTO.getIdiomas().getFirst());
            libro.setDescargas(libroDTO.getDescargas());

            AutorDTO autorDTO = libroDTO.getAutores().getFirst();
            Autor autor = new Autor();
            autor.setNombre(autorDTO.name());
            autor.setNacimiento(autorDTO.birth_year());
            autor.setFallecimiento(autorDTO.death_year());

            libro.setAutor(autor);
            Optional<Libro> existente = libroRepo.findByTituloIgnoreCase(libro.getTitulo());

            if (existente.isPresent()) {
                System.out.println("⚠️ Ya existe un libro con ese título. No se guardará duplicado.");
                return;
            }
            libroRepo.save(libro);

        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }

    public void listarLibros() {
        libroRepo.findAll().forEach(System.out::println);
    }

    public void listarPorIdioma(String idioma) {
        libroRepo.findByIdiomaIgnoreCase(idioma).forEach(System.out::println);
    }

    public void listarAutores() {
        autorRepo.findAll().forEach(System.out::println);
    }

    public void listarAutoresVivosEn(Integer anio) {
        autorRepo.encontrarVivosEnAnio(anio).forEach(System.out::println);
    }
}
