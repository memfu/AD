package controller;

import dao.AutorDAO;
import dao.EditorialDAO;
import dao.LibreriaDAO;
import dao.LibroDAO;
import database.HibernateUtil;
import model.Autor;
import model.Editorial;
import model.Libreria;
import model.Libro;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LibroController {

    private AutorDAO autorDAO;
    private EditorialDAO editorialDAO;
    private LibreriaDAO libreriaDAO;
    private LibroDAO libroDAO;

    public LibroController(){
        // Necesito que el controller inicialice cada uno de los DAO, para poder ejecutar los métodos
        autorDAO = new AutorDAO();
        editorialDAO = new EditorialDAO();
        libreriaDAO = new LibreriaDAO();
        libroDAO = new LibroDAO();
    }

    /*
    Lógica de negocio
    - Para dar de alta un libro, el autor y la editorial tienen que estar dados de alta antes
    - Para dar de alta una librería, el libro tiene que estar dado de alta
     */

    public void darAltaAutor(Autor autor){
        autorDAO.agregarAutor(autor);
        System.out.println("Autor agregado correctamente.");
    }

    public void darAltaEditorial(Editorial editorial){
        editorialDAO.crearEditorial(editorial);
        System.out.println("Editorial agregada correctamente.");
    }

    public void agregarLibro(String titulo, double precio, String nombreEditorial, String nombreAutor, String apellidosAutor){
        Editorial editorial = editorialDAO.buscarPorNombre(nombreEditorial);
        Autor autor = autorDAO.buscarPorNombreYApellidos(nombreAutor, apellidosAutor);

        if (editorial == null) {
            System.out.println("La editorial '" + nombreEditorial + "' no existe. Debe crearse antes.");
            return;
        }
        if (autor == null) {
            System.out.println("El autor '" + nombreAutor + " " + apellidosAutor + "' no existe. Debe crearse antes.");
            return;
        }

        Libro libro = new Libro(titulo,precio);
        libro.setEditorial(editorial);
        libro.setAutor(autor);

        libroDAO.crearLibro(libro);
        System.out.println("Libro agregado correctamente: " + titulo);

    }

    public void agregarLibreria(Libreria libreria){
        libreriaDAO.crearLibreria(libreria);
        System.out.println("La librería " + libreria.getNombre() + " se ha agregado correctamente.");
    }

    public void agregarLibrosLibrerias() {
        /*
        Como no tenemos una clase para la tabla renacida de catalogo_librerias, de forma excepcional
        iniciamos la session dentro del controller
        */
        Session session;
        // En este caso uso .openSession() porque sino me da problemas de que la session ya está abierta con los getAll
        session = new HibernateUtil().getSessionFactory().openSession();
        session.beginTransaction();
        // Primero obtengo la lista de libros y librerías
        List<Libro> libros = libroDAO.getAllLibros();
        List<Libreria> librerias = libreriaDAO.getAllLibrerias();
        // Inicio un random
        Random random = new Random();

        for (Libreria libreria : librerias) {
            // Mezcla los libros aleatoriamente
            Collections.shuffle(libros, random);

            // Selecciona 6 libros para esta librería
            List<Libro> librosAsignados = libros.subList(0, 6);

            // Itera cada libro de la lista de libros con orden aleatorio
            for (Libro libro : librosAsignados) {
                // Inserta en la tabla catalogo_librerias
                session.createNativeQuery("INSERT INTO catalogo_librerias (libreria_id, libro_id) VALUES (:libreria, :libro)")
                        .setParameter("libreria", libreria.getId())
                        .setParameter("libro", libro.getId())
                        .executeUpdate();
            }
        }
        session.close();
    }

    public void listaLibrosConEditorialyAutor() {
        // Itera por la lista de libros
        for (Libro libro : libroDAO.getAllLibros()) {
            // Imprime el id y el titulo del libro
            System.out.println(libro.getId() + " - " + libro.getTitulo()
                    + "\n\t Editorial: " + libro.getEditorial().getNombre()
                    + "\n\t Autor: " +libro.getAutor().getNombre() + " " + libro.getAutor().getApellidos());
        }
    }
    public void listaAutoresYObras() {
        // Itera por la lista de autores
        for (Autor autor : autorDAO.getListaAutores()) {
            // Imprime el nombre y los apellidos del autor
            System.out.println("* "+ autor.getNombre() + " " + autor.getApellidos());
            // Itera por los libros escritos por ese autor
            for (Libro librosEscrito : autor.getLibrosEscritos()) {
                // Imprime el título del libro
                System.out.println("\t- " + librosEscrito.getTitulo() + ".");
            }

        }
    }

    public void listaLibreriasConLibros(){
        List<Libreria> listaLibrerias = libreriaDAO.libreriasConLibros();

        for (Libreria libreria : listaLibrerias) {
            System.out.println("* Librería: " + libreria.getNombre());
            for (Libro libro : libreria.getColeccionLibros()) {
                System.out.println("\t- " + libro.getTitulo());
            }
        }
    }

    public void listaLibrosEnLibrerias() {
        List<Libro> listaLibros = libroDAO.librosConLibrerias();

        for (Libro libro : listaLibros) {
            System.out.println("* Libro: " + libro.getTitulo());
            for (Libreria libreria : libro.getListaLibrerias()) {
                System.out.println("\t- Disponible en: " + libreria.getNombre());
            }
        }
    }
}
