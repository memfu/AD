package com.example.LibreriaAPIS.service;

import com.example.LibreriaAPIS.model.Autor;
import com.example.LibreriaAPIS.model.Editorial;
import com.example.LibreriaAPIS.model.Libro;
import com.example.LibreriaAPIS.repository.AutorRepository;
import com.example.LibreriaAPIS.repository.EditorialRepository;
import com.example.LibreriaAPIS.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// implementación del servicio, la lógica contra la BBDD
@Service
public class LibroServiceImp implements LibroService{
    @Autowired // para inicializar el repository sin que me meta todos los métodos
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditorialRepository editorialRepository;
    @Override
    public void agregarLibro(Libro libro) {
        // Si el autor que se ha metido en el libro no es null ni 0
        if (libro.getAutor() != null && libro.getAutor().getId() != 0) {
            // Comprueba si existe el autor en la bbdd comparando los id
            if (!autorRepository.existsById(libro.getAutor().getId())) {
                System.out.println("Autor no encontrado, el libro no será guardado.");
                return; // Sale del método sin guardar el libro
            }
            // Si el autor existe, lo obtiene y lo asigna al libro
            Autor autorExistente = autorRepository.findById(libro.getAutor().getId()).get();
            libro.setAutor(autorExistente);
        } else {
                libro.setAutor(null);     // Asegura que no se intente guardar el id de un autor que no existe
        }

        if (libro.getEditorial() != null && libro.getEditorial().getId() != 0) {
            // Comprueba si existe la editorial en la bbdd comparando los id
            if (!editorialRepository.existsById(libro.getEditorial().getId())) {
                System.out.println("Editorial no encontrada, el libro no será guardado.");
                return; // Sale del método sin guardar el libro
            }
            // Si la editorial existe, lo obtiene y lo asigna al libro
            Editorial editorialExistente = editorialRepository.findById(libro.getEditorial().getId()).get();
            libro.setEditorial(editorialExistente);
        } else {
            libro.setEditorial(null); // Asegura que no se intente guardar el id de una editorial que no existe
        }
        libroRepository.save(libro);
    }

    @Override
    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    @Override
    public List<Libro> findByTitulo(String titulo) {
        return libroRepository.findByTitulo(titulo);
    }

    @Override
    public List<Libro> findByAutor(String nombreAutor, String apellidosAutor) {
        return libroRepository.findByAutor(nombreAutor, apellidosAutor);
    }

    @Override
    public List<Libro> findByListaLibrerias_Nombre(String nombreLibreria) {
        return libroRepository.findByListaLibrerias_Nombre(nombreLibreria);
    }

    @Override
    public List<Libro> findByEditorial_Id(int idEditorial) {
        return libroRepository.findByEditorial_Id(idEditorial);
    }
}
