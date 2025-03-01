package controllers;

import java.util.Scanner;

public class Validador {
    public Scanner scannerNum = new Scanner(System.in);


    public int checkIntAnswer() {
        int respuesta;
        while (true) {
            if (scannerNum.hasNextInt()) {
                respuesta = scannerNum.nextInt();
                scannerNum.nextLine(); // Consume el salto de línea
                return respuesta;
            } else {
                System.out.println("Entrada inválida. Por favor, introduzca un número válido.");
                scannerNum.nextLine(); // Limpia el buffer
            }
        }
    }

    public float checkFloatAnswer() {
        float respuesta;
        while (true) {
            if (scannerNum.hasNextFloat()) {
                respuesta = scannerNum.nextFloat();
                scannerNum.nextLine(); // Consume el salto de línea
                return respuesta;
            } else {
                System.out.println("Entrada inválida. Por favor, introduzca un número válido.");
                scannerNum.nextLine(); // Limpia el buffer
            }
        }
    }

    public void cerrarScanner() {
        scannerNum.close();
    }
}

