package virtuosi.digitales.ballatores.liter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import virtuosi.digitales.ballatores.liter.ui.MenuPrincipal;

/**
 * Aplicación principal de LiterAlura.
 *
 * Esta aplicación permite buscar libros desde la API de Gutendex,
 * almacenar la información en PostgreSQL y realizar consultas.
 *
 * @author HEAM
 */
@SpringBootApplication
public class LiterAluraApplication {
    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(MenuPrincipal menu) {
        return args -> menu.mostrarMenu();
    }
}

