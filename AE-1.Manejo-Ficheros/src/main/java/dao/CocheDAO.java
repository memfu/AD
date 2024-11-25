package dao;

import model.Coche;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CocheDAO {
    Coche coche = new Coche();
    Scanner scanner = new Scanner(System.in);
    File file = new File("src/main/java/ficheros/coches.dat");
    public static ArrayList<Coche> listadoCoches;


    public ArrayList<Coche> checkFile() {
        listadoCoches = new ArrayList<>(); // Inicializamos listadoCoches para evitar NullPointerException dentro del while

        if (file.exists() && file.canRead()) {
            ObjectInputStream objectInputStream = null; // Inicializado en null para después poder cerrarlo dentro del ámbito del try/catch

            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(file));
                Coche cocheFichero;
                while ((cocheFichero = (Coche) objectInputStream.readObject()) != null) { // mientras lea algo diferente a nulo
                    listadoCoches.add(cocheFichero);
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al crear el objeto para el archivo " + file.getName() + ". Error: " + e.getMessage());
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

    public void addCar (int idNr, String plate, String brand, String model, String colour) {
        Coche cochenuevo = new Coche(idNr,plate, brand, model, colour);
        listadoCoches.add(cochenuevo);
        System.out.println("El coche se ha añadido correctamente.");
        System.out.println(listadoCoches);
    }

    public void showCars() {
        if (listadoCoches.isEmpty()) {
            System.out.println("No hay coches en el listado.");
        } else {
            System.out.println("Aquí tiene el listado de coches:");
            for (Coche car : listadoCoches) {
                System.out.println("\t" + car.toString());
            }
        }
    }

    public void deleteOrSearchCar (String accion, int id) {
        boolean encontrado = false;
        for (Coche car : listadoCoches) {
            if (car.getId() == id) {
                if (accion.equalsIgnoreCase("eliminar")) {
                    listadoCoches.remove(car);
                    System.out.println("El coche con id " + id + " ha sido eliminado correctamente de la lista.");
                } else if (accion.equalsIgnoreCase("buscar")) {
                    System.out.println(car.toString());
                }
            encontrado = true;
            break;
            }
        }
        if (!encontrado) {
            System.out.println("No se ha encontrado ningún coche con id " + id + ".");
        }
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

    public int generarIdUnico() {
        if (listadoCoches.isEmpty()) {
            return 1;
        } else {
            int ultimoId = listadoCoches.getLast().getId();
            return ultimoId + 1;
        }
    }

    public boolean checkMatriculaExistente(String matricula) {
        for (Coche car : listadoCoches) {
            if(car.getMatricula().equalsIgnoreCase(matricula)) {
                System.out.println("Esa matrícula ya existe. Por favor, introduzca una matrícula nueva.");
                return true; // La matrícula existe ya en la lista de coches
            }
        }
        return false;
    }

}
