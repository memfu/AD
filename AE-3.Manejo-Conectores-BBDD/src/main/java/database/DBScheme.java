package database;

public interface DBScheme {

    // Interfaz
    // 1. Conectar dos clases que no tienen nada que ver con los metodos abstractos que tiene dicha interfaz
    // 2. Almacén de constantes variables y con valor, es decir, son finales (no de métodos)
    // public final String nombre ="asd";
    String HOST = "127.0.0.1";
    String PORT = "3306";
    String DB_NAME = "poolCoches";

    String TAB_CAR = "coches";
    String TAB_PASS = "pasajeros";

    String COL_ID = "id";

    String COL_CAR_MARCA = "marca";
    String COL_CAR_MODEL = "modelo";
    String COL_CAR_COLOR = "color";
    String COL_CAR_CV = "caballos";
    String COL_CAR_PRICE = "precio";
    String COL_CAR_NRPASS = "nrPasajeros";

    String COL_PASS_NAME = "nombre";
    String COL_PASS_AGE = "edad";
    String COL_PASS_PESO = "peso";
    String COL_PASS_CAR = "cocheAsignado";

}
