import controllers.Validador;
import dao.AlumnoDAO;
import dao.ProfesorDAO;
import helpers.PreguntaRespuesta;
import model.Usuario;

import java.util.ArrayList;
import java.util.Scanner;

public class Entrada {
    private static Scanner scanner = new Scanner(System.in);
    private static Validador validador = new Validador();
    private static PreguntaRespuesta cuestionario = new PreguntaRespuesta();

    private static Usuario usuarioGeneral = new Usuario();

    private static String menu = "Elija una de las siguientes opciones introduciendo el número:\n" +
            "1. Insertar un alumno.\n" +
            "2. Insertar un profesor.\n" +
            "3. Mostrar datos de usuarios.\n" +
            "4. Obtener alumnos por correo.\n" +
            "5. Obtener profesores por edad.\n" +
            "6. Actualizar calificación de profesor.\n" +
            "7. Dar de baja a alumnos aprobados.\n" +
            "8. Salir.\n";

    public static void main(String[] args) {
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        ProfesorDAO profesorDAO = new ProfesorDAO();

        int respuestaMenu = 0;

        do {
            respuestaMenu = validador.checkIntAnswer(menu);

            switch (respuestaMenu) {
                case 1:
                    usuarioGeneral = cuestionario.cuestionarioGeneral();
                    int calificacion = validador.checkIntAnswer("Calificación: ");
                    String grado = cuestionario.askQuestion(scanner,"Grado: ");

                    alumnoDAO.addAlumno(usuarioGeneral,grado,calificacion);
                    break;
                case 2:
                    usuarioGeneral = cuestionario.cuestionarioGeneral();
                    String titulo = cuestionario.askQuestion(scanner,"Título: ");
                    System.out.println("Inserte las asignaturas del profesor separadas por coma.");
                    String entrada = scanner.nextLine();
                    ArrayList<String> asignaturas = new ArrayList<>();

                    // Divide la entrada en partes y agrega al ArrayList
                    for (String elemento : entrada.split(",")) {
                        asignaturas.add(elemento.trim()); // Elimina espacios adicionales y agrega al ArrayList
                    }

                    profesorDAO.addProf(usuarioGeneral,titulo,asignaturas);
                    break;
                case 3:
                    boolean respuestaValida = false;
                    while (!respuestaValida) {
                        String respuestaMostrar = cuestionario.askQuestion(scanner,"¿Qué usuarios desea ver: alumnos, profesores o ambos?");
                        if (respuestaMostrar.equalsIgnoreCase("alumnos")) {
                            alumnoDAO.showAlumnos();
                            respuestaValida = true;
                        } else if (respuestaMostrar.equalsIgnoreCase("profesores")) {
                            profesorDAO.showProf();
                            respuestaValida = true;
                        } else if (respuestaMostrar.equalsIgnoreCase("ambos")) {
                            alumnoDAO.showAlumnos();
                            profesorDAO.showProf();
                            respuestaValida = true;
                        } else {
                            System.out.println("Respuesta no válida.");
                        }
                    }
                    break;
                case 4:
                    String correo = cuestionario.askQuestion(scanner,"Introduzca el correo del alumno/a que desea obtener.");
                    alumnoDAO.searchAlumnoByMail(correo);
                    break;
                case 5:
                    int minAge = validador.pedirEdad("Edad mínima (inclusive): ");
                    int maxAge = validador.pedirEdad("Edad máxima (inclusive): ");
                    profesorDAO.searchProfByAge(minAge,maxAge);
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    validador.cerrarScanner();
                    System.out.println("El programa se ha cerrado con éxito.");
                    break;
                default:
                    break;
            }

        } while (respuestaMenu !=8);

    }
}
