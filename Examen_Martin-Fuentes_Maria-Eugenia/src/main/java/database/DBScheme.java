package database;

public interface DBScheme {
    String HOST = "127.0.0.1";
    // Añadido el PORT
    String PORT = "3306";
    String DB_NAME = "usuarios";

    String TAB_USER = "usuarios";

    String COL_ID = "id";

    String COL_USER_NAME = "nombre";
    String COL_USER_SURNAME = "apellido";

    String COL_USER_MAIL = "correo";
    String COL_USER_PASS = "pass";

}
