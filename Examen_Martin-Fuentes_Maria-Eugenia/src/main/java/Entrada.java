import controllers.Validador;
import dao.UsuarioDAO;
import model.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) throws SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Validador validador = new Validador();
        Scanner scanner = new Scanner(System.in);

        String mail = "";
        String pass = "";

        String menu = "\n----- MENÚ -----\n" +
                "1) Registrar nuevo usuario\n" +
                "2) Listar usuarios\n" +
                "3) Comprobar credenciales\n" +
                "4) Exportar usuarios\n" +
                "5) Terminar el programa\n" +
                "Indique el número del menú cuya acción quiere ejecutar.";               ;
        int respuesta = 0;

        do {
            System.out.println(menu);
            respuesta = validador.checkIntAnswer();
            switch (respuesta) {
                case 1:
                    System.out.println("Inserte el nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.println("Inserte el apellido: ");
                    String apellido = scanner.nextLine();
                    mail = usuarioDAO.checkMailUnique();
                    System.out.println("Inserte su contraseña: ");
                    pass = scanner.nextLine();

                    usuarioDAO.addUser(new Usuario(nombre, apellido, mail, pass));
                    break;
                case 2:
                    ArrayList<Usuario> usuarios = usuarioDAO.listUsers();
                    for (Usuario user : usuarios) {
                        System.out.println("ID: " + user.getId() + ", Nombre: " + user.getNombre());
                    }
                    break;
                case 3:
                    boolean login = false;
                    boolean bloqueo = false;
                    int intentos = 4;

                    do {
                        System.out.println("Inserte su correo electrónico: ");
                        mail = scanner.nextLine();
                        System.out.println("Inserte su contraseña: ");
                        pass = scanner.nextLine();
                        try {
                            login = usuarioDAO.getLogin(mail, pass);
                            intentos--;
                            if (login) {
                                System.out.println("Login correcto, adelante");
                            } else {
                                if (intentos == 0) {
                                    bloqueo = true;
                                }
                                System.out.println("Login incorrecto, fallo");
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    } while ( intentos !=0 && !login);

                    if (intentos == 0 && bloqueo) {
                        System.out.println("Caja bloqueada.");
                    }

                    break;
                case 4:
                    usuarioDAO.listUsers();
                    usuarioDAO.exportUsers();
                    break;
                case 5:
                    validador.cerrarScanner();
                    System.out.println("El programa se ha cerrado con éxito.");
                    break;
                default:
                    System.out.println("Introduzca un número del menú.");
                    break;
            }
        } while (respuesta!=5);

    }

}
