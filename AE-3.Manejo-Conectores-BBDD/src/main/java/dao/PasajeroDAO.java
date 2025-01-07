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

    private Scanner scanner = new Scanner(System.in);
    private Validador validador = new Validador();
    CocheDAO cocheDAO = new CocheDAO();


    public void addPassenger(Pasajero pasajero) throws SQLException {
        String query = String.format("INSERT into %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                DBScheme.TAB_PASS,
                DBScheme.COL_PASS_NAME, DBScheme.COL_PASS_AGE, DBScheme.COL_PASS_PESO, DBScheme.COL_PASS_CAR
        );
        connection = new DBConnection().getConnection();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, pasajero.getNombre());
        preparedStatement.setInt(2, pasajero.getEdad());
        preparedStatement.setFloat(3, pasajero.getPeso());
        preparedStatement.setInt(4, -1);
        preparedStatement.execute();
        new DBConnection().closeConnection();
    }

    public void deletePass(int id) throws SQLException {
        if (checkPassIdExistente(id)) {
            updatePassList();
            for (Pasajero passenger : listadoPasajeros) {
                int cocheID = passenger.getCocheID();
                if (cocheID != -1) {
                    cocheDAO.updateNrPass("eliminar", cocheID);
                }
            }
            String queryDELETE = "DELETE FROM %s WHERE %s = ?";
            connection = new DBConnection().getConnection();
            String query = String.format(queryDELETE,
                    DBScheme.TAB_PASS,
                    DBScheme.COL_ID_PASS
            );
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            updatePassList();
            System.out.println("Se ha eliminado el pasajero con id " + id + ".");

            new DBConnection().closeConnection();
        }
    }
    public void updatePassengerCar(int idPass, int idCar) throws SQLException {
        updatePassList();
        for (Pasajero passenger : listadoPasajeros) {
            int cocheID = passenger.getCocheID();
            if (passenger.getId()== idPass && cocheID != -1) {
                cocheDAO.updateNrPass("eliminar", cocheID);
            }
        }

        String queryUPDATE = "UPDATE %s SET %s = ? WHERE %s = ?";
        connection = new DBConnection().getConnection();
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

    public void showPassengers() throws SQLException {
        updatePassList();
        if (listadoPasajeros.isEmpty()) {
            System.out.println("No hay pasajeros en el listado.");
        } else {
            for (Pasajero passenger : listadoPasajeros) {
                System.out.println((passenger.toString()) + "\n");
            }
        }
    }

    public void showPassengersByCarId(int carId) throws SQLException {
        updatePassList();
        if (listadoPasajeros.isEmpty()) {
            System.out.println("No hay pasajeros en el listado.");
        } else {
            for (Pasajero passenger : listadoPasajeros) {
                if (passenger.getCocheID() == carId) {
                    System.out.println((passenger.toString()) + "\n");
                }
            }
        }
    }

    public void showPassengerById (int id) throws SQLException {
        if (checkPassIdExistente(id)) {
            updatePassList();
            for (Pasajero passenger : listadoPasajeros) {
                if (passenger.getId() == id) {
                    System.out.println((passenger.toString()) + "\n");
                }
            }
        } else {
            System.out.println("No se ha encontrado ningún pasajero con id " + id + ".");
        }
    }

    private ArrayList<Pasajero> updatePassList() throws SQLException {
        listadoPasajeros = new ArrayList<>();
        String query = "SELECT * FROM " + DBScheme.TAB_PASS;
        connection = new DBConnection().getConnection();
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt(DBScheme.COL_ID_PASS);
            String nombre = resultSet.getString(DBScheme.COL_PASS_NAME);
            int age = resultSet.getInt(DBScheme.COL_PASS_AGE);
            float peso = resultSet.getFloat(DBScheme.COL_PASS_PESO);
            int assignedCar = resultSet.getInt(DBScheme.COL_PASS_CAR);
            listadoPasajeros.add(new Pasajero(id,nombre,age,peso,assignedCar));
        }
        new DBConnection().closeConnection();
        return listadoPasajeros;
    }
    public boolean checkPassIdExistente(int passId) throws SQLException {
        connection = new DBConnection().getConnection();
        preparedStatement = connection.prepareStatement(String.format("SELECT %s FROM %s WHERE %s=?",
                DBScheme.COL_ID_PASS, DBScheme.TAB_PASS, DBScheme.COL_ID_PASS));
        preparedStatement.setInt(1, passId);
        resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

}
