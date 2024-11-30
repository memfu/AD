package controllers;

import java.util.ArrayList;
import java.util.Scanner;

public class Validador {
    private Scanner scanner;

    // Constructor para inicializar el Scanner
    public Validador() {
        scanner = new Scanner(System.in);
    }

    public int checkIntAnswer(String pregunta) {
        System.out.println(pregunta);
        int respuesta;
        while (true) {
            if (scanner.hasNextInt()) {
                respuesta = scanner.nextInt();
                scanner.nextLine(); // Consume el salto de línea
                return respuesta;
            } else {
                System.out.println("Entrada inválida. Por favor, introduzca un número válido.");
                scanner.nextLine(); // Limpia el buffer
            }
        }
    }
    public int pedirEdad(String mensaje) {
        int edad;
        do {
            System.out.print(mensaje);
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, introduzca un número válido.");
                scanner.nextLine();
            }
            edad = scanner.nextInt();
            scanner.nextLine();
            if (edad < 0) System.out.println("La edad debe ser positiva.");
        } while (edad < 0);
        return edad;
    }

    public ArrayList<String> pedirArrayListAsignaturas() {
        ArrayList<String> asignaturas = new ArrayList<>();
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.println("Introduzca las asignaturas separadas por comas:");
            String entrada = scanner.nextLine();

            // Valida que contiene al menos una coma
            if (!entrada.contains(",")) {
                System.out.println("Error: Las asignaturas deben estar separadas por comas. Inténtelo de nuevo.");
                continue;
            }

            // Divide y procesa las asignaturas
            String[] partes = entrada.split(",");
            for (String parte : partes) {
                asignaturas.add(parte.trim()); // Elimina espacios adicionales
            }

            // Valida que no hay elementos vacíos
            if (asignaturas.isEmpty() || asignaturas.stream().anyMatch(String::isEmpty)) {
                System.out.println("Error: Una o más asignaturas están vacías. Intenta de nuevo.");
                asignaturas.clear(); // Limpia la lista antes de intentarlo de nuevo
            } else {
                entradaValida = true; // La entrada es válida
            }
        }

        return asignaturas;
    }


    public void cerrarScanner() {
        scanner.close();
    }
}
