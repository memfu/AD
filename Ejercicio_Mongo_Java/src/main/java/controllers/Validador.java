package controllers;

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


    public void cerrarScanner() {
        scanner.close();
    }
}
