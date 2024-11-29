package helpers;

import controllers.Validador;
import dao.UsuarioDAO;
import model.Usuario;

import java.util.Scanner;

public class PreguntaRespuesta {
    Scanner scanner = new Scanner(System.in);

    public String askQuestion(Scanner scanner, String pregunta){
        System.out.println(pregunta);
        String respuesta = scanner.next();
        scanner.nextLine(); // consume la entrada inválida y limpia el buffer
        return respuesta;
    }

    public Usuario cuestionarioGeneral() {
        Usuario nuevoUsuario;
        PreguntaRespuesta cuestionario = new PreguntaRespuesta();
        Validador validador = new Validador();

        String nombre = cuestionario.askQuestion(scanner,"Nombre: ");
        int edad = validador.pedirEdad("Edad: ");
        String genero = cuestionario.askQuestion(scanner,"Género: ");
        System.out.println("Rating: ");
        float rating = scanner.nextFloat();

        String correo = cuestionario.askQuestion(scanner,"Correo electrónico: ");
        String telefono = cuestionario.askQuestion(scanner,"Teléfono: ");

        nuevoUsuario = new Usuario(nombre,edad,rating,genero,correo,telefono);

        return nuevoUsuario;
    }
}
