package controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Coche;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GestionFicheros {
    File file = new File("src/main/java/ficheros/coches.dat");
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Coche> listadoCoches;

    public GestionFicheros(ArrayList<Coche> listadoCoches) {
        this.listadoCoches = listadoCoches;
    }

    public ArrayList<Coche> checkFile() {
        listadoCoches = new ArrayList<>(); // Inicializamos listadoCoches para evitar NullPointerException dentro del while

        if (file.exists() && file.canRead()) {
            ObjectInputStream objectInputStream = null; // Inicializado en null para después poder cerrarlo dentro del ámbito del try/catch

            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(file));
                /*
                   El bucle while seguirá ejecutándose indefinidamente hasta que se encuentre una condición
                   que lo interrumpa explícitamente, como ocurre con el break en caso de una EOFException
                   (que se va a dar cuando se llegue al final del archivo y no encuentre nada: null).
                 */
                while (true) {
                    try {
                        Coche cocheFichero = (Coche) objectInputStream.readObject();
                        listadoCoches.add(cocheFichero);
                    } catch (EOFException e) {
                        break; // Fin del archivo alcanzado
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al leer el archivo " + file.getName() + ". Error: " + e.getMessage());
            } finally {
                try {
                    objectInputStream.close();
                } catch (IOException | NullPointerException e) {
                    System.out.println("Error al cerrar el flujo de entrada: " + e.getMessage());
                }
            }
        }
        return listadoCoches;
    }

    public void exportCSV(){
        // Dependiendo de la configuración de Excel, los elementos van separados por ";" o por ",". Se le da la opción al usuario de elegir el elemento de separación
        boolean separadorCorrecto = false;
        String separador;
        System.out.println("Inserte el separador que necesite para su archivo csv (escriba \",\" o \";\" sin comillas).");

        do {
            separador = scanner.nextLine();
            if (separador.equalsIgnoreCase("coma") || separador.equals(",")) {
                separador = ",";
                separadorCorrecto = true;
            } else if (separador.equalsIgnoreCase("punto y coma") || separador.equals(";")){
                separador = ";";
                separadorCorrecto = true;
            } else {
                System.out.println("Por favor, introduzca un separador válido: escriba \",\" o \";\" sin comillas.");
            }
        } while (!separadorCorrecto);

        File ficheroCSV = new File("src/main/java/ficheros/coches.csv");
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(ficheroCSV));
            // Primero se escriben los encabezados de las columnas
            writer.write("ID"+separador+"MATRICULA"+separador+"MARCA"+separador+"MODELO"+separador+"COLOR");
            writer.newLine();

            for (Coche coche : listadoCoches) {
                writer.write(coche.getId() + separador + coche.getMatricula() + separador + coche.getMarca()
                        + separador + coche.getModelo() + separador + coche.getColor());
                writer.newLine();
            }
            System.out.println("Archivo CSV creado exitosamente en: " + ficheroCSV);

        } catch (IOException e) {
            System.out.println("Error al crear el objeto para el archivo " + ficheroCSV.getName() + ". Error: " + e.getMessage());
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el buffer de escritura: " + e.getMessage());
            }
        }
    }

    public void writeFinalFile () {
        ObjectOutputStream objectOutputStream = null;

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el fichero: " + e.getMessage());;
            }
        }

        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            for (Coche coche : listadoCoches) {
                objectOutputStream.writeObject(coche);
            }
        } catch (IOException e) {
            System.out.println("Error al crear el objeto para el archivo " + file.getName() + ". Error: " + e.getMessage());
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error al cerrar el flujo de salida: " + e.getMessage());
            }
        }
    }
}
