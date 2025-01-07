import controllers.Validador;
import dao.CocheDAO;
import dao.PasajeroDAO;
import model.Coche;
import model.Pasajero;

import java.sql.SQLException;
import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) throws SQLException {
        CocheDAO car = new CocheDAO();
        PasajeroDAO passenger = new PasajeroDAO();

        Validador validador = new Validador();
        Scanner scanner = new Scanner(System.in);

        String menu = "\n----- MENÚ -----\n" +
                "1) Añadir nuevo coche\n" +
                "2) Borrar coche por id\n" +
                "3) Consulta coche por id\n" +
                "4) Modificar coche por id\n" +
                "5) Listado de coches\n" +
                "6) Gestión de pasajeros\n" +
                "7) Terminar el programa\n" +
                "Indique el número del menú cuya acción quiere ejecutar.";

        String submenu = "\n----- SUBMENÚ: Gestión de pasajeros -----\n" +
                "1) Añadir nuevo pasajero\n" +
                "2) Borrar pasajero por id\n" +
                "3) Consulta pasajero por id\n" +
                "4) Listado de pasajeros\n" +
                "5) Añadir pasajero a coche\n" +
                "6) Eliminar pasajero de un coche\n" +
                "7) Listado todos los pasajeros de un coche\n" +
                "8) Volver al menú principal\n" +
                "Indique el número del menú cuya acción quiere ejecutar.";
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
                    int idToDelete;
                    do {
                        // Comprobación si el identificador del coche existe
                        System.out.println("Inserte el id del coche que desea borrar.");
                        idToDelete = validador.checkIntAnswer();
                    } while (!car.checkCarIdExistente(idToDelete));

                    car.deleteCar(idToDelete);
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
                    do {
                        System.out.println(submenu);
                        respuesta = validador.checkIntAnswer();
                        switch (respuesta) {
                            case 1:
                                System.out.println("Inserte el nombre: ");
                                String nombre = scanner.nextLine();
                                System.out.println("Inserte la edad: ");
                                int age = validador.checkIntAnswer();
                                System.out.println("Inserte el peso: ");
                                float peso = validador.checkFloatAnswer();

                                passenger.addPassenger(new Pasajero(nombre,age,peso));
                                break;
                            case 2:
                                System.out.println("Inserte el id del pasajero que desea borrar.");
                                int idToDeletePass = validador.checkIntAnswer();
                                passenger.deletePass(idToDeletePass);
                                break;
                            case 3:
                                System.out.println("Inserte el id del pasajero que desea consultar.");
                                int idToSeekPass = validador.checkIntAnswer();
                                passenger.showPassengerById(idToSeekPass);
                                break;
                            case 4:
                                passenger.showPassengers();
                                break;
                            case 5:
                                System.out.println("Estos son los coches disponibles.");
                                car.showCars();

                                System.out.println("Inserte el id del pasajero que desea añadir. " +
                                        "\n Recuerde que un pasajero solo puede ir asociado a un coche." +
                                        "\n En caso de que el pasajero ya tenga un coche asignado, este se sobreescribirá.");
                                int idPassToAdd = validador.checkIntAnswer();

                                int idCarToAdd;
                                do {
                                    // Comprobación si el identificador del coche existe
                                    System.out.println("Inserte el id del coche: ");
                                    idCarToAdd = validador.checkIntAnswer();
                                } while (!car.checkCarIdExistente(idCarToAdd));

                                passenger.updatePassengerCar(idPassToAdd, idCarToAdd);
                                car.updateNrPass("AGREGAR",idCarToAdd);
                                break;
                            case 6:
                                System.out.println("Estos son los coches disponibles.");
                                car.showCars();

                                System.out.println("Inserte el id del pasajero que desea eliminar. ");
                                int idPassToDelete = validador.checkIntAnswer();
                                int idCarToDelete;
                                do {
                                    // Comprobación si el identificador del coche existe
                                    System.out.println("Inserte el id del coche: ");
                                    idCarToDelete = validador.checkIntAnswer();
                                } while (!car.checkCarIdExistente(idCarToDelete));

                                passenger.updatePassengerCar(idPassToDelete, -1);
                                car.updateNrPass("ELIMINAR",idCarToDelete);
                                break;
                            case 7:
                                int idCarToSeek;
                                do {
                                    // Comprobación si el identificador del coche existe
                                    System.out.println("Inserte el id del coche: ");
                                    idCarToSeek = validador.checkIntAnswer();
                                } while (!car.checkCarIdExistente(idCarToSeek));

                                System.out.println("Estos son los pasajeros asociados al coche con id " + idCarToSeek + ".");
                                passenger.showPassengersByCarId(idCarToSeek);

                                break;
                            case 8:
                                System.out.println("Ha salido del submenú con éxito.");
                                break;
                            default:
                                System.out.println("Introduzca un número del submenú.");
                                break;
                        }
                    } while (respuesta!=8);

                    break;
                case 7:
                    validador.cerrarScanner();
                    System.out.println("El programa se ha cerrado con éxito.");
                    break;
                default:
                    System.out.println("Introduzca un número del menú.");
                    break;
            }
        } while (respuesta!=7);

    }
}
