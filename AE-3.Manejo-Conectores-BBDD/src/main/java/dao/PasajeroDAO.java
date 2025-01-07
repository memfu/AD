package dao;

import controllers.Validador;
import database.DBConnection;
import database.DBScheme;
import model.Coche;
import model.Pasajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class PasajeroDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public static ArrayList<Pasajero> listadoPasajeros  = new ArrayList<>();

    private Scanner scanner;
    private Validador validador;
    CocheDAO cocheDAO = new CocheDAO();


    public void addPassenger(Pasajero pasajero) throws SQLException {
        connection = new DBConnection().getConnection();
        String query = String.format("INSERT into %s (%s,%s,%s) VALUES (?,?,?)",
                DBScheme.TAB_PASS,
                DBScheme.COL_PASS_NAME, DBScheme.COL_PASS_AGE, DBScheme.COL_PASS_PESO
        );
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, pasajero.getNombre());
        preparedStatement.setInt(2, pasajero.getEdad());
        preparedStatement.setFloat(3, pasajero.getPeso());
        preparedStatement.execute();
        new DBConnection().closeConnection();


    }

    public void managePassenger(String accion, int id) throws SQLException {
        boolean encontrado = false;
        for (Pasajero pasajero : listadoPasajeros) {
            if (pasajero.getId() == id) {
                encontrado = true;
                int cocheID = pasajero.getCocheID();
                if (accion.equalsIgnoreCase("eliminar")) {
                    if (cocheID != -1) {
                        cocheDAO.updateNrPass("eliminar",cocheID);
                    }
                    listadoPasajeros.remove(pasajero);
                    System.out.println("El pasajero con id " + id + " ha sido eliminado correctamente de la lista.");
                } else if (accion.equalsIgnoreCase("buscar")) {
                    System.out.println(pasajero.toString());
                }
            }
        }
        if (!encontrado) {
            System.out.println("No se ha encontrado ningún pasajero con id " + id + ".");
        }
    }

    public void updatePassengerCar(int idPass, int idCar) throws SQLException {
        connection = new DBConnection().getConnection();
        String queryUPDATE = "UPDATE %s SET %s = ? WHERE %s = ?";
        String query = String.format(queryUPDATE,
                DBScheme.TAB_PASS,
                DBScheme.COL_PASS_CAR,
                DBScheme.COL_ID_PASS
        );
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idCar);
        preparedStatement.setInt(2, idPass);
        preparedStatement.execute();
        System.out.println("Se ha asociado correctamente el usuario con el coche deseado.");
        new DBConnection().closeConnection();

    }

    public String showPassengers() throws SQLException {
        connection = new DBConnection().getConnection();
        String query = "SELECT * FROM " + DBScheme.TAB_PASS;
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        new DBConnection().closeConnection();

        return getResultados(resultSet);
    }

    public String showPassengersByCarId(int carId) throws SQLException {
        connection = new DBConnection().getConnection();
        String query = "SELECT * FROM %s WHERE %s = ?";
        String querySeek = String.format(query,
                DBScheme.TAB_PASS,
                DBScheme.COL_PASS_CAR,
                DBScheme.COL_ID_PASS
        );
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, carId);
        resultSet = preparedStatement.executeQuery();
        new DBConnection().closeConnection();

        return getResultados(resultSet);
    }

    private String getResultados(ResultSet datosResultantes) throws SQLException {
        connection = new DBConnection().getConnection();
        StringBuilder resultado = new StringBuilder(); // Para acumular los resultados en formato texto.
        while (datosResultantes.next()){
            String nombre = resultSet.getString(DBScheme.COL_PASS_NAME);
            int age = resultSet.getInt(DBScheme.COL_PASS_AGE);
            float peso = resultSet.getFloat(DBScheme.COL_PASS_PESO);
            int carID = resultSet.getInt(DBScheme.COL_PASS_CAR);
            listadoPasajeros.add(new Pasajero(nombre,age,peso,carID));
        }
        new DBConnection().closeConnection();

        for (Pasajero pasajero : listadoPasajeros) {
            resultado.append(pasajero.toString()).append("\n");
        }
        return resultado.toString();
    }

}
