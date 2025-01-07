package dao;

import database.DBConnection;
import database.DBScheme;
import model.Usuario;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UsuarioDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public static ArrayList<Usuario> listadoUsuarios = new ArrayList<>();
    // Si se crea en el constructor no se puede cerrar la conexión, por eso hay que evitar crearla en el constructor
    /*
    public UsuarioDAO() {connection = new DBConnection().getConnection();}
     */


    public void addUser(Usuario usuario) throws SQLException {
        String query = String.format("INSERT into %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                DBScheme.TAB_USER,
                DBScheme.COL_USER_NAME, DBScheme.COL_USER_SURNAME, DBScheme.COL_USER_MAIL, DBScheme.COL_USER_PASS
        );
        connection = new DBConnection().getConnection();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, usuario.getNombre());
        preparedStatement.setString(2, usuario.getApellido());
        preparedStatement.setString(3, usuario.getCorreo());
        preparedStatement.setString(4, usuario.getPass());
        boolean fallo = preparedStatement.execute();
        new DBConnection().closeConnection();
    }

    public ArrayList<Usuario> listUsers() throws SQLException {
        String query = String.format("SELECT %s, %s FROM %s",
                                    DBScheme.COL_ID, DBScheme.COL_USER_NAME, DBScheme.TAB_USER);
        connection = new DBConnection().getConnection();
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        new DBConnection().closeConnection();
        return getResultados(resultSet);
    }

    private ArrayList<Usuario> getResultados(ResultSet datosResultantes) throws SQLException {
        listadoUsuarios = new ArrayList<>();
        while (datosResultantes.next()){
            int id = resultSet.getInt(DBScheme.COL_ID);
            String nombre = resultSet.getString(DBScheme.COL_USER_NAME);
            listadoUsuarios.add(mapearUser(id, nombre));
        }
        return listadoUsuarios;
    }

    public void exportUsers() {
        if (listadoUsuarios == null || listadoUsuarios.isEmpty()) {
            System.out.println("No hay usuarios para exportar.");
            return;
        }
        ObjectOutputStream objectOutputStream = null;
        File file = new File("src/main/java/ficheros/usuarios.obj");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el fichero: " + e.getMessage());;
            }
        }

        try {
        objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        for (Usuario user : listadoUsuarios) {
            objectOutputStream.writeObject(user);
            System.out.println("El usuario con ID " + user.getId() + " se ha incluido en el fichero");;
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

    public String checkMailUnique () throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean mailUnique = true;
        String mail = "";

        do {
            System.out.println("Inserte el correo: ");
            mail = scanner.nextLine();
            preparedStatement = connection.prepareStatement(String.format("SELECT %s FROM %s WHERE %s=?",
                    DBScheme.COL_ID, DBScheme.TAB_USER, DBScheme.COL_USER_MAIL));
            preparedStatement.setString(1,mail);
            resultSet = preparedStatement.executeQuery();
            mailUnique = resultSet.next();
            if (mailUnique) {
                System.out.println("Ya hay un usuario registrado con ese correo.");
            }
        } while (mailUnique);

        return mail;
    }

    public boolean getLogin(String mail, String pass) throws SQLException {
        preparedStatement = connection.prepareStatement(String.format("SELECT %s FROM %s WHERE %s=? AND %s=?",
                DBScheme.COL_ID, DBScheme.TAB_USER, DBScheme.COL_USER_MAIL, DBScheme.COL_USER_PASS));
        preparedStatement.setString(1,mail);
        preparedStatement.setString(2,pass);
        resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    private Usuario mapearUser(int id, String nombre){
        return new Usuario(id,nombre);
    }






}
