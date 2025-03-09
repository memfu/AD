package com.example.LibreriaAPIS.service;

import com.example.LibreriaAPIS.model.Libro;

import java.util.List;

public interface LibroService {
    // escribo todos los métodos que quiero llamar desde el controller
    // de estos métodos solo se escribe la firma (el nombre)

    void agregarLibro(Libro libro);

    List<Libro> getAllLibros();

    List<Libro> findByTitulo(String titulo);

    List<Libro> findByAutor_Nombre(String nombreAutor);

    List<Libro> findByListaLibrerias_Nombre(String nombreLibreria);

    List<Libro> findByEditorial_Id(int idEditorial);


}
