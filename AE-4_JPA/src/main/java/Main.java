import controller.LibroController;
import dao.AutorDAO;
import dao.LibroDAO;
import model.Autor;
import model.Editorial;
import model.Libro;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        /*LibroController libroController = new LibroController();
        libroController.agregarLibro(new Libro("Dracula", 10.50, new Editorial("Pamplinas"),new Autor("Bram", "Stoker") ));*/

        AutorDAO autorDAO = new AutorDAO();
        autorDAO.agregarAutor(new Autor("Bram", "Stoker"));
    }
}
