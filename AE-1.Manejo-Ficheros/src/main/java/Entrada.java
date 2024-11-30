import controllers.GestionFicheros;
import controllers.Validador;
import dao.CocheDAO;
import model.Coche;

import java.util.ArrayList;
import java.util.Scanner;

public class Entrada {

    public static void main (String [] args) {
        GestionFicheros gestionFicheros = new GestionFicheros();

        // Comprueba si existe un fichero coches.dat. Si sí, exporta el contenido a un ArrayList, si no inicializa el ArrayList
        ArrayList<Coche> listadoCoches = gestionFicheros.checkFile();

        // Pasamos el ArrayList compartido a ambas clases
        CocheDAO car = new CocheDAO(listadoCoches);
        // Configura el listado también en GestionFicheros
        gestionFicheros.setListadoCoches(listadoCoches);


        Validador validador = new Validador();
        Scanner scanner = new Scanner(System.in);

        String menu = "\n----- MENÚ -----\n" +
                "1) Añadir nuevo coche\n" +
                "2) Borrar coche por id\n" +
                "3) Consulta coche por id\n" +
                "4) Listado de coches\n" +
                "5) Exportar coches a archivo CSV\n" +
                "6) Terminar el programa\n" +
                "Indique el número del menú cuya acción quiere ejecutar.";               ;
        int respuesta = 0;

        do {
            System.out.println(menu);
            respuesta = validador.checkIntAnswer();
            switch (respuesta) {
                case 1:
                    String matricula;
                    do {
                        // Comprobación si la matrícula ya existe
                        System.out.println("Inserte la matrícula: ");
                        matricula = scanner.nextLine();
                    } while (car.checkMatriculaExistente(matricula));

                    System.out.println("Inserte la marca: ");
                    String marca = scanner.nextLine();
                    System.out.println("Inserte el modelo: ");
                    String modelo = scanner.nextLine();
                    System.out.println("Inserte el color: ");
                    String color = scanner.nextLine();

                    int nuevoId = car.generarIdUnico();

                    car.addCar(nuevoId, matricula, marca, modelo, color);
                    break;
                case 2:
                    System.out.println("Inserte el id del coche que desea borrar.");
                    int idToDelete = validador.checkIntAnswer();
                    car.deleteOrSearchCar("ELIMINAR", idToDelete);
                    break;
                case 3:
                    System.out.println("Inserte el id del coche que desea consultar.");
                    int idToSeek = validador.checkIntAnswer();
                    car.deleteOrSearchCar("BUSCAR",idToSeek);
                    break;
                case 4:
                    car.showCars();
                    break;
                case 5:
                    gestionFicheros.exportCSV();
                    break;
                case 6:
                    gestionFicheros.writeFinalFile();
                    validador.cerrarScanner();
                    System.out.println("El programa se ha cerrado con éxito.");
                    break;
                default:
                    break;
            }
        } while (respuesta!=6);
    }
}
