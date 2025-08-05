// ========================================
// INTERFAZ DE CONSOLA
// ========================================
package virtuosi.digitales.ballatores.liter.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import virtuosi.digitales.ballatores.liter.service.LibroService;

import java.util.Scanner;

/**
 * Interfaz de consola para interactuar con el usuario.
 *
 * @author HEAM
 */
@Component
public class MenuPrincipal {

    @Autowired
    private LibroService servicio;

    private final Scanner teclado = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion =0;
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Listar todos los libros");
            System.out.println("3. Listar libros por idioma");
            System.out.println("4. Listar autores");
            System.out.println("5. Listar autores vivos en año");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            String entrada= teclado.nextLine();
            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue;
            }
           // teclado.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese el título: ");
                    String titulo = teclado.nextLine();
                    servicio.buscarYGuardarLibro(titulo);
                }
                case 2 -> servicio.listarLibros();
                case 3 -> {
                    System.out.print("Idioma (ej: 'en', 'es'): ");
                    String idioma = teclado.nextLine();
                    servicio.listarPorIdioma(idioma);
                }
                case 4 -> servicio.listarAutores();
                case 5 -> {
                    System.out.print("Año: ");
                    String anioTexto = teclado.nextLine().trim();
                    try {
                        int anio = Integer.parseInt(anioTexto);
                        servicio.listarAutoresVivosEn(anio);
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, ingrese un año válido.");
                    }
                }
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }
}