import controller.LibroController;
import dao.AutorDAO;
import dao.LibroDAO;
import model.Autor;
import model.Editorial;
import model.Libreria;
import model.Libro;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        LibroController libroController = new LibroController();

        // Dar de alta 3 autores
       /* libroController.darAltaAutor(new Autor("Michael", "Ende", LocalDate.of(1929,11,12)));
        libroController.darAltaAutor(new Autor("J.K.", "Rowling", LocalDate.of(1965,7,31)));
        libroController.darAltaAutor(new Autor("Gabriel", "García Márquez", LocalDate.of(1927,3,6)));*/

        // Dar de alta 3 editoriales
       /* libroController.darAltaEditorial(new Editorial("Salamandra", "Avd. Jumilla s/n 28087 Madrid"));
        libroController.darAltaEditorial(new Editorial("Penguin", "Avd. Reyes Católicos nr. 118 42524 Barcelona"));*/
        // Dar de alta 8 libros pertenecientes a uno de los autores y editoriales previos
        /*libroController.agregarLibro("Harry Potter y la Piedra Filosofal", 12.99,
                "Salamandra", "J.K.", "Rowling");
        libroController.agregarLibro("Harry Potter y la Cámara de los Secretos", 15.99,
                "Salamandra", "J.K.", "Rowling");
        libroController.agregarLibro("Harry Potter y el Prisionero de Azkaban", 17.99,
                "Salamandra", "J.K.", "Rowling");
        libroController.agregarLibro("Momo", 11.99,
                "Penguin", "Michael", "Ende");
        libroController.agregarLibro("La historia interminable", 10.99,
                "Salamandra", "Michael", "Ende");
        libroController.agregarLibro("Cien años de soledad", 11.99,
                "Salamandra", "Gabriel", "García Márquez");
        libroController.agregarLibro("Crónica de una muerte anunciada", 5.49,
                "Penguin", "Gabriel", "García Márquez");
        libroController.agregarLibro("El amor en los tiempos del cólera", 18.49,
                "Salamandra", "Gabriel", "García Márquez");*/

        /*
        2 librerías, cada librería tendrá 6 libros dados de alta previamente.
        NOTA: He cambiado el enunciado de 4 a 6 libros por librería, porque es posible
        que el mismo libro esté en más de una librería
        */
       /* libroController.agregarLibreria(new Libreria("Papiros", "José Luis Perales", "Plaza del Aljibico nr 7 30520 Jumilla"));
        libroController.agregarLibreria(new Libreria("Los Álamos", "Maricarmen López", "Avd. de la Constitución nr 56 30005 Murcia"));*/
        // Agregar libros de forma random a las librerias
/*
        libroController.agregarLibrosLibrerias();
*/
        //Mostrar todos los libros dados de alta, con su editorial y su autor
        libroController.listaLibrosConEditorialyAutor();
        //Mostrar todos los autores dados de alta, con sus libros asociados
        libroController.listaAutoresYObras();
        //Mostrar todas las librerías, con solamente sus libros asociados
        libroController.listaLibreriasConLibros();
        //Mostrar todos los libros dados de alta, y en la librería en la que están.
        libroController.listaLibrosEnLibrerias();
    }
}
