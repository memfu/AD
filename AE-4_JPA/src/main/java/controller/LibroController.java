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

    public void darAltaAutor(Autor autor){
        autorDAO.agregarAutor(autor);
    }

    public void darAltaEditorial(Editorial editorial){
        editorialDAO.crearEditorial(editorial);
    }

    public void agregarLibro(String titulo, double precio, String nombreEditorial, String nombreAutor, String apellidosAutor){
        Editorial editorial = editorialDAO.buscarPorNombre(nombreEditorial);
        Autor autor = autorDAO.buscarPorNombreYApellidos(nombreAutor, apellidosAutor);

        if (editorial == null) {
            System.out.println("❌ La editorial '" + nombreEditorial + "' no existe. Debe crearse antes.");
            return;
        }
        if (autor == null) {
            System.out.println("❌ El autor '" + nombreAutor + " " + apellidosAutor + "' no existe. Debe crearse antes.");
            return;
        }

        Libro libro = new Libro(titulo,precio);
        libro.setEditorial(editorial);
        libro.setAutor(autor);

        libroDAO.crearLibro(libro);
        System.out.println("✅ Libro agregado correctamente: " + titulo);

    }

    public void agregarLibreria(Libreria libreria){
        //TODO

    }

    public void listaLibrosConEditorialyAutor() {
        // Consigue la lista de libros
        List<Libro> listaLibros = libroDAO.getAllLibros();
        // Itera por la lista de libros
        for (Libro libro : listaLibros) {
            // Imprime el id y el titulo del libro
            System.out.println(libro.getId() + " - " + libro.getTitulo()
                    + "\n\t Editorial: " + libro.getEditorial().getNombre()
                    + "\n\t Autor: " +libro.getAutor().getNombre() + " " + libro.getAutor().getApellidos());
        }
    }
    public void listaAutoresYObras() {
        // Consigue la lista de autores
        List<Autor> listaAutores = autorDAO.getListaAutores();
        // Itera por la lista de autores
        for (Autor autor : listaAutores) {
            // Imprime el nombre y los apellidos del autor
            System.out.println(autor.getNombre() + " " + autor.getLibrosEscritos());
            // Itera por los libros escritos por ese autor
            for (Libro librosEscrito : autor.getLibrosEscritos()) {
                // Imprime el título del libro
                System.out.println("\t- " + librosEscrito.getTitulo() + ".");
            }

        }
    }
}
