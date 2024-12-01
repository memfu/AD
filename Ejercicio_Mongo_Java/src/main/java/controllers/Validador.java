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
                System.out.println("Entrada inválida. Por favor, introduzca un número entero válido.");
                scanner.nextLine(); // Limpia el buffer
            }
        }
    }

    public double checkDoubleAnswer(String pregunta) {
        System.out.println(pregunta);
        double respuesta;
        while (true) {
            if (scanner.hasNextDouble()) {
                respuesta = Math.round(scanner.nextDouble() * 100)/100.0;
                scanner.nextLine(); // Consume el salto de línea
                return respuesta;
            } else {
                System.out.println("Entrada inválida. Por favor, introduzca un número válido.");
                scanner.nextLine(); // Limpia el buffer
            }
        }
    }
    public int pedirEdad(String pregunta) {
        int edad;
        do {
            System.out.print(pregunta);
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

    public ArrayList<String> pedirArrayList(String pregunta) {
        ArrayList<String> arrayList = new ArrayList<>();
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.println(pregunta);
            String entrada = scanner.nextLine();

            // Valida que contiene al menos una coma
            if (!entrada.contains(",")) {
                System.out.println("Error: Las arrayList deben estar separadas por comas. Inténtelo de nuevo.");
                continue;
            }

            // Divide y procesa las arrayList
            String[] partes = entrada.split(",");
            for (String parte : partes) {
                arrayList.add(parte.trim()); // Elimina espacios adicionales
            }

            // Valida que no hay elementos vacíos
            if (arrayList.isEmpty() || arrayList.stream().anyMatch(String::isEmpty)) {
                System.out.println("Error: Una o más arrayList están vacías. Intenta de nuevo.");
                arrayList.clear(); // Limpia la lista antes de intentarlo de nuevo
            } else {
                entradaValida = true; // La entrada es válida
            }
        }

        return arrayList;
    }


    public void cerrarScanner() {
        scanner.close();
    }
}
