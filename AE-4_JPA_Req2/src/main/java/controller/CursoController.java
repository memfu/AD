package controller;

import dao.CentroDAO;
import dao.CursoDAO;
import dao.MadreDAO;
import dao.PrimogenitoDAO;
import database.HibernateUtil;
import model.Centro;
import model.Curso;
import model.Madre;
import model.Primogenito;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CursoController {
    CentroDAO centroDAO;
    CursoDAO cursoDAO;
    MadreDAO madreDAO;
    PrimogenitoDAO primogenitoDAO;

    public void darAltaCurso(Curso curso){
        cursoDAO.agregarCurso(curso);
        System.out.println("Curso agregado correctamente.");
    }

    public void darAltaCentro(Centro centro){
        centroDAO.agregarCentro(centro);
        System.out.println("Centro agregado correctamente.");
    }

    public void agregarMadre(String nombre, String apellidos, String direccion, LocalDate fechaNac, String nombreCentro){
        Centro centro = centroDAO.buscarPorNombre(nombreCentro);

        if (centro == null) {
            System.out.println("El centro '" + nombreCentro + "' no existe. Debe crearse antes.");
            return;
        }

        Madre madre = new Madre(nombre,apellidos,direccion,fechaNac);
        madre.setCentro(centro);

        madreDAO.agregarMadre(madre);
        System.out.println("Madre agregada correctamente: " + nombre + " " + apellidos);

    }

    public void agregarPrimogenito(String nombre, String apellidos, LocalDate fechaNac, String nombreMadre, String apellidosMadre){
        Madre madre = madreDAO.buscarPorNombreYApellidos(nombreMadre,apellidosMadre);

        if (madre == null) {
            System.out.println("La madre " + nombreMadre + " " + apellidosMadre + " no existe. Debe crearse antes.");
            return;
        }

        Primogenito primogenito = new Primogenito(nombre,apellidos,fechaNac);
        primogenito.setMadre(madre);

        primogenitoDAO.agregarPrimogenito(primogenito);
        System.out.println("Primogénito agregado correctamente: " + nombre + " " + apellidos);

    }

    public void agregarMadresCursos() {
        /*
        Como no tenemos una clase para la tabla renacida, de forma excepcional
        iniciamos la session dentro del controller
        */
        Session session;
        // En este caso uso .openSession() porque sino me da problemas de que la session ya está abierta con los getAll
        session = new HibernateUtil().getSessionFactory().openSession();
        session.beginTransaction();
        // Primero obtengo la lista de madres y cursos
        List<Madre> madres = madreDAO.getAllMadres();
        List<Curso> cursos = cursoDAO.getAllCursos();
        // Inicio un random
        Random random = new Random();

        for (Madre madre : madres) {
            // Mezcla los cursos aleatoriamente
            Collections.shuffle(cursos, random);

            // Selecciona 6 cursos para esta madre
            List<Curso> cursosAsignados = cursos.subList(0, 6);

            // Itera cada curso de la lista con orden aleatorio
            for (Curso curso : cursosAsignados) {
                // Inserta en la tabla cursos_participantes
                session.createNativeQuery("INSERT INTO cursos_participantes (madre_id, curso_id) VALUES (:madre, :curso)")
                        .setParameter("madre", madre.getId())
                        .setParameter("curso", curso.getId())
                        .executeUpdate();
            }
        }
        //session.getTransaction().commit();
        session.close();
    }

    public void listaMadresConCursos() {
        // Itera por la lista de madres
        for (Madre madre : madreDAO.getAllMadres()) {
            // Imprime el nombre y los apellidos de la madre
            System.out.println(madre.getId() + " - " + madre.getNombre() + " " + madre.getApellidos());
            // Itera por los cursos en los que participa esa madre
            for (Curso cursosApuntados : madre.getListaCursos()) {
                // Imprime el nombre del curso
                System.out.println("\t- " + cursosApuntados.getNombre() + ".");
            }
        }
    }

    public void listaCursosConMadres(){
        List<Curso> listaCursos = cursoDAO.CursosConMadres();

        for (Curso curso : listaCursos) {
            System.out.println("* Curso: " + curso.getNombre());
            for (Madre madre : curso.getMadresApuntadas()) {
                System.out.println("\t- " + madre.getNombre() + " " + madre.getApellidos());
            }
        }
    }
}
