package controllers;

import model.Coche;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class GestionFicheros {

    public void readObject (File archivo) {
        File datosCoches = new File("src/main/java/ficheros/coches.dat");
        ArrayList<Objects> listadoCoches = new ArrayList<>();

        // Comprobar si existe el fichero coches.dat
        ObjectInputStream objectInputStream = null;
        if (datosCoches.exists()){
            // lee el .dat y mete los objetos en un ArrayList
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(archivo));
                Coche coche = (Coche)objectInputStream.readObject();

                } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                System.out.println("No se ha encontrado la clase destino.");
                 } catch (IOException e) {
                System.out.println("Error en la lectura del fichero.");
                }

        } else {
            // Hacer ArrayList vacía
        }

    }

    public void showList () {

    }

    public void writeFile () {

    }
}
