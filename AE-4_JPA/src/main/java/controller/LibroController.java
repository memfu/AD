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
import org.hibernate.internal.build.AllowSysOut;

import java.util.List;

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

    public void agregarLibro(Libro libro){
        List<Editorial> listaEditoriales = editorialDAO.getListaEditoriales();
        List<Autor> listaAutores = autorDAO.getListaAutores();

        for (Editorial editorial : listaEditoriales) {
            if (editorial == libro.getEditorial()) {
                for (Autor autor : listaAutores) {
                    if (autor == libro.getAutor()) {
                        libroDAO.crearLibro(libro,editorial,autor);
                    } else {
                        System.out.println("El autor no se ha agregado todavía. Por favor, cree el autor primero.");
                    }
                }
            } else {
                System.out.println("La editorial no se ha agregado todavía. Por favor, cree la editorial primero.");
            }
        }

    }

    public void agregarLibreria(Libreria libreria){
        List<Libro> listaLibros = libroDAO.getAllLibros();

    }
}
