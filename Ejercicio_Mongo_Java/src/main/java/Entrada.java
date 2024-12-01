import controllers.Validador;
import dao.AlumnoDAO;
import dao.ProfesorDAO;
import database.DBScheme;
import helpers.PreguntaRespuesta;
import model.Usuario;

import java.util.ArrayList;
import java.util.Scanner;

public class Entrada {
    private static Scanner scanner = new Scanner(System.in);
    private static Validador validador = new Validador();
    private static PreguntaRespuesta preguntaRespuesta = new PreguntaRespuesta();

    private static Usuario usuarioGeneral = new Usuario();

    private static String menu = "\n --- MENÚ ---\n" +
            "Elija una de las siguientes opciones introduciendo el número:\n" +
            "1. Insertar un/a estudiante.\n" +
            "2. Insertar un/a docente.\n" +
            "3. Mostrar datos de usuarios.\n" +
            "4. Obtener estudiante por correo.\n" +
            "5. Obtener docentes por edad.\n" +
            "6. Actualizar calificación de docente por correo.\n" +
            "7. Dar de baja a estudiantes aprobados.\n" +
            "8. Salir.\n";

    public static void main(String[] args) {
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        ProfesorDAO profesorDAO = new ProfesorDAO();

        int respuestaMenu = 0;

        do {
            respuestaMenu = validador.checkIntAnswer(menu);

            switch (respuestaMenu) {
                case 1:
                    usuarioGeneral = preguntaRespuesta.cuestionarioGeneral();
                    int calificacion = validador.checkIntAnswer("Calificación: ");
                    String grado = preguntaRespuesta.askQuestion(scanner,"Grado: ");

                    alumnoDAO.addAlumno(usuarioGeneral,grado,calificacion);
                    break;
                case 2:
                    usuarioGeneral = preguntaRespuesta.cuestionarioGeneral();
                    String titulo = preguntaRespuesta.askQuestion(scanner,"Título: ");
                    ArrayList<String> asignaturas = validador.pedirArrayList("Introduzca las asignaturas separadas por comas:");
                    profesorDAO.addProf(usuarioGeneral,titulo,asignaturas);
                    break;
                case 3:
                    boolean respuestaValida = false;
                    while (!respuestaValida) {
                        String respuestaMostrar = preguntaRespuesta.askQuestion(scanner,"¿Qué usuarios desea ver: estudiantes, docentes o ambos?");
                        if (respuestaMostrar.equalsIgnoreCase("estudiantes")) {
                            alumnoDAO.showAlumnos();
                            respuestaValida = true;
                        } else if (respuestaMostrar.equalsIgnoreCase("docentes")) {
                            profesorDAO.showProf();
                            respuestaValida = true;
                        } else if (respuestaMostrar.equalsIgnoreCase("ambos")) {
                            System.out.println("---ALUMNOS---");
                            alumnoDAO.showAlumnos();
                            System.out.println("\n---PROFESORES---");
                            profesorDAO.showProf();
                            respuestaValida = true;
                        } else {
                            System.out.println("Respuesta no válida.");
                        }
                    }
                    break;
                case 4:
                    String correo = preguntaRespuesta.askQuestion(scanner,"Introduzca el correo del/de la estudiante que desea obtener.");
                    alumnoDAO.searchAlumnoByMail(correo);
                    break;
                case 5:
                    int minAge = validador.pedirEdad("Edad mínima (inclusive): ");
                    int maxAge = validador.pedirEdad("Edad máxima (inclusive): ");
                    profesorDAO.searchProfByAge(minAge,maxAge);
                    break;
                case 6:
                    String mailProf = preguntaRespuesta.askQuestion(scanner,"Introduzca el correo del/de la docente que desea actualizar.");
                    if (profesorDAO.checkExistingProf(DBScheme.keyMail,mailProf)) {
                        double newRating = validador.checkDoubleAnswer("Introduzca la nueva calificación del/de la docente.");
                        profesorDAO.updateProf(mailProf,newRating);
                    }
                    break;
                case 7:
                    alumnoDAO.deleteAlumnoAprobado(5);
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
