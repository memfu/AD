package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    public Connection getConnection() {
        if(connection==null) {
            newConnection();
        }
        // System.out.println("Conexión obtenida.");
        return connection;

    }

    public void newConnection(){
        String url = "jdbc:mysql://" + DBScheme.HOST + ":" + DBScheme.PORT + "/" + DBScheme.DB_NAME;

        try {
            connection = DriverManager.getConnection(url,"root", "");
        } catch (SQLException e) {
            System.out.println("Error en la conexión de la base de datos.");
            // Añadidos los mensajes específicos de los errores
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }

    }

    public void closeConnection(){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión.");
            } finally {
                connection = null;
            }
        }

    }

}
