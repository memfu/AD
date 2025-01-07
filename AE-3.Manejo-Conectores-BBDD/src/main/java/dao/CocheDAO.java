package dao;

import controllers.Validador;
import database.DBConnection;
import database.DBScheme;
import model.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CocheDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public static ArrayList<Coche> listadoCoches  = new ArrayList<>();

    private Scanner scanner;
    private Validador validador;

    public void addCar(Coche coche) throws SQLException {
        String query = String.format("INSERT into %s (%s,%s,%s,%s,%s) VALUES (?,?,?,?,?)",
                DBScheme.TAB_CAR,
                DBScheme.COL_CAR_MARCA, DBScheme.COL_CAR_MODEL, DBScheme.COL_CAR_COLOR, DBScheme.COL_CAR_CV, DBScheme.COL_CAR_PRICE
                );
        connection = new DBConnection().getConnection();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, coche.getMarca());
        preparedStatement.setString(2, coche.getModelo());
        preparedStatement.setString(3, coche.getColor());
        preparedStatement.setInt(4, coche.getCaballos());
        preparedStatement.setFloat(5, coche.getPrecio());
        preparedStatement.execute();
        new DBConnection().closeConnection();
    }

    public void manageCar(String accion, int id) throws SQLException {
        boolean encontrado = false;
        for (Coche car : listadoCoches) {
            if (car.getId() == id) {
                encontrado = true;
                if (accion.equalsIgnoreCase("eliminar")) {
                    listadoCoches.remove(car);
                    System.out.println("El coche con id " + id + " ha sido eliminado correctamente de la lista.");
                } else if (accion.equalsIgnoreCase("buscar")) {
                    System.out.println(car.toString());
                } else if (accion.equalsIgnoreCase("actualizar")) {
                    updateCar(id);
                }
            }
        }
        if (!encontrado) {
            System.out.println("No se ha encontrado ningún coche con id " + id + ".");
        }
    }

    public void updateCar(int id) throws SQLException {
        String queryUPDATE = "UPDATE %s SET %s = ? WHERE %s = ?";
        connection = new DBConnection().getConnection();


        System.out.println("Indique que campo de los siguientes quiere modificar: marca, modelo, color, caballos o precio.");
        String respuestaMod = scanner.nextLine();

        System.out.println("Indique el nuevo valor.");
        if (!respuestaMod.equalsIgnoreCase("marca") &&
                !respuestaMod.equalsIgnoreCase("modelo") &&
                !respuestaMod.equalsIgnoreCase("color") &&
                !respuestaMod.equalsIgnoreCase("caballos") &&
                !respuestaMod.equalsIgnoreCase("precio")) {
            System.out.println("Campo no reconocido.");
        } else {
            if (respuestaMod.equalsIgnoreCase("caballos")) {
                int cvNew = validador.checkIntAnswer();
                String query = String.format(queryUPDATE,
                        DBScheme.TAB_CAR,
                        DBScheme.COL_CAR_CV,
                        DBScheme.COL_ID
                );
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, cvNew);
                preparedStatement.setInt(2, id);

            } else if (respuestaMod.equalsIgnoreCase("precio")) {
                float priceNew = validador.checkFloatAnswer();
                String query = String.format(queryUPDATE,
                        DBScheme.TAB_CAR,
                        DBScheme.COL_CAR_PRICE,
                        DBScheme.COL_ID
                );
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setFloat(1, priceNew);
                preparedStatement.setInt(2, id);
            } else if (respuestaMod.equalsIgnoreCase("marca")){
                String newValue = scanner.nextLine();
                String query = String.format(queryUPDATE,
                        DBScheme.TAB_CAR,
                        DBScheme.COL_CAR_MARCA,
                        DBScheme.COL_ID
                );
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, newValue);
                preparedStatement.setInt(2, id);
            } else if (respuestaMod.equalsIgnoreCase("modelo")){
                String newValue = scanner.nextLine();
                String query = String.format(queryUPDATE,
                        DBScheme.TAB_CAR,
                        DBScheme.COL_CAR_MODEL,
                        DBScheme.COL_ID
                );
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, newValue);
                preparedStatement.setInt(2, id);
            } else if (respuestaMod.equalsIgnoreCase("color")){
                String newValue = scanner.nextLine();
                String query = String.format(queryUPDATE,
                        DBScheme.TAB_CAR,
                        DBScheme.COL_CAR_COLOR,
                        DBScheme.COL_ID
                );
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, newValue);
                preparedStatement.setInt(2, id);
            }

            preparedStatement.execute();
            new DBConnection().closeConnection();
        }

    }

    public void updateNrPass(String accion, int id) throws SQLException {
        String queryUPDATE = "UPDATE %s SET %s = ? WHERE %s = ?";
        connection = new DBConnection().getConnection();

        for (Coche car : listadoCoches) {
            if (car.getId() == id) {
                int currentNrPass = car.getNrPass();
                if (accion.equalsIgnoreCase("eliminar")) {
                    String query = String.format(queryUPDATE,
                            DBScheme.TAB_CAR,
                            DBScheme.COL_CAR_NRPASS,
                            DBScheme.COL_ID
                    );
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, currentNrPass-1);
                    preparedStatement.setInt(2, id);
                    System.out.println("Se ha quitado un pasajero al coche con id " + id + ".");
                } else if (accion.equalsIgnoreCase("agregar")) {
                    String query = String.format(queryUPDATE,
                            DBScheme.TAB_CAR,
                            DBScheme.COL_CAR_NRPASS,
                            DBScheme.COL_ID
                    );
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, currentNrPass+1);
                    preparedStatement.setInt(2, id);
                    System.out.println("Se ha añadido un pasajero al coche con id " + id + ".");
                    System.out.println(car.toString());
                }
                preparedStatement.execute();
                new DBConnection().closeConnection();
            } else {
                System.out.println("No existe ningún coche con el id " + id + ".");
            }
        }
    }

    public String showCars() throws SQLException {
        String query = "SELECT * FROM " + DBScheme.TAB_CAR;
        connection = new DBConnection().getConnection();
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        StringBuilder resultado = new StringBuilder(); // Para acumular los resultados en formato texto.
        while (resultSet.next()){
            String marca = resultSet.getString(DBScheme.COL_CAR_MARCA);
            String modelo = resultSet.getString(DBScheme.COL_CAR_MODEL);
            String color = resultSet.getString(DBScheme.COL_CAR_COLOR);
            int cv = resultSet.getInt(DBScheme.COL_CAR_CV);
            int precio = resultSet.getInt(DBScheme.COL_CAR_PRICE);
            int nrPass = resultSet.getInt(DBScheme.COL_CAR_NRPASS);
            listadoCoches.add(new Coche(marca,modelo,color,cv,precio,nrPass));
        }

        for (Coche car : listadoCoches) {
            resultado.append(car.toString()).append("\n");
        }
        new DBConnection().closeConnection();

        return resultado.toString();

    }


    /*public String showCars() throws SQLException {
        String query = "SELECT * FROM " + DBScheme.TAB_CAR;
        connection = new DBConnection().getConnection();

        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        new DBConnection().closeConnection();
        return getResultados(resultSet);
    }

    private String getResultados(ResultSet datosResultantes) throws SQLException {
        StringBuilder resultado = new StringBuilder(); // Para acumular los resultados en formato texto.
        while (datosResultantes.next()){
            String marca = resultSet.getString(DBScheme.COL_CAR_MARCA);
            String modelo = resultSet.getString(DBScheme.COL_CAR_MODEL);
            String color = resultSet.getString(DBScheme.COL_CAR_COLOR);
            int cv = resultSet.getInt(DBScheme.COL_CAR_CV);
            int precio = resultSet.getInt(DBScheme.COL_CAR_PRICE);
            int nrPass = resultSet.getInt(DBScheme.COL_CAR_NRPASS);
            listadoCoches.add(new Coche(marca,modelo,color,cv,precio,nrPass));
        }

        for (Coche car : listadoCoches) {
            resultado.append(car.toString()).append("\n");
        }
        return resultado.toString();
    }*/

    public boolean checkCarIdExistente(int carId) {
        for (Coche car : listadoCoches) {
            if(car.getId() != carId) {
                System.out.println("No existe ningún coche con ese identificador. Por favor, introduzca un identificador válido.");
                return false; // La matrícula existe ya en la lista de coches
            }
        }
        return true;
    }
}
