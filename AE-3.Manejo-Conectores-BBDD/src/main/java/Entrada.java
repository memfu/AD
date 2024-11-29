import controllers.Validador;
import dao.CocheDAO;
import model.Coche;

import java.sql.SQLException;
import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) throws SQLException {
        CocheDAO car = new CocheDAO();

        Validador validador = new Validador();
        Scanner scanner = new Scanner(System.in);

        String menu = "\n----- MENÚ -----\n" +
                "1) Añadir nuevo coche\n" +
                "2) Borrar coche por id\n" +
                "3) Consulta coche por id\n" +
                "4) Modificar coche por id\n" +
                "5) Listado de coches\n" +
                "6) Terminar el programa\n" +
                "Indique el número del menú cuya acción quiere ejecutar.";               ;
        int respuesta = 0;

        do {
            System.out.println(menu);
            respuesta = validador.checkIntAnswer();
            switch (respuesta) {
                case 1:
                    System.out.println("Inserte la marca: ");
                    String marca = scanner.nextLine();
                    System.out.println("Inserte el modelo: ");
                    String modelo = scanner.nextLine();
                    System.out.println("Inserte el color: ");
                    String color = scanner.nextLine();
                    System.out.println("Inserte los caballos: ");
                    int cv = validador.checkIntAnswer();
                    System.out.println("Inserte el precio: ");
                    float price = validador.checkFloatAnswer();

                    car.addCar(new Coche(marca, modelo, color, cv, price));
                    break;
                case 2:
                    System.out.println("Inserte el id del coche que desea borrar.");
                    int idToDelete = validador.checkIntAnswer();
                    car.manageCar("ELIMINAR", idToDelete);
                    break;
                case 3:
                    System.out.println("Inserte el id del coche que desea consultar.");
                    int idToSeek = validador.checkIntAnswer();
                    car.manageCar("BUSCAR",idToSeek);
                    break;
                case 4:
                    System.out.println("Inserte el id del coche que desea actualizar.");
                    int idToUpdate = validador.checkIntAnswer();
                    car.manageCar("ACTUALIZAR",idToUpdate);
                    break;
                case 5:
                    car.showCars();
                    break;
                case 6:
                    validador.cerrarScanner();
                    System.out.println("El programa se ha cerrado con éxito.");
                    break;
                default:
                    System.out.println("Introduzca un número del menú.");
                    break;
            }
        } while (respuesta!=6);

    }
}
