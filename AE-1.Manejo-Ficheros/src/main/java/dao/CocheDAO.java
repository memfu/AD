package dao;

import model.Coche;

import java.util.ArrayList;

public class CocheDAO {
    private ArrayList<Coche> listadoCoches;

    public CocheDAO(ArrayList<Coche> listadoCoches) {
        this.listadoCoches = listadoCoches;
    }

    public void addCar (int idNr, String plate, String brand, String model, String colour) {
        Coche cochenuevo = new Coche(idNr,plate, brand, model, colour);
        listadoCoches.add(cochenuevo);
        System.out.println("El coche con matrícula " + cochenuevo.getMatricula() + ", de la marca "
                + cochenuevo.getMarca() + " y modelo " + cochenuevo.getModelo() + " se ha añadido correctamente.");
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
