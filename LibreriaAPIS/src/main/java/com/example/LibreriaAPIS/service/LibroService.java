package com.example.LibreriaAPIS.service;

import com.example.LibreriaAPIS.model.Libro;

public interface LibroService {
    // escribo todos los métodos que quiero llamar desde el controller
    // de estos métodos solo se escribe la firma (el nombre)

    Libro agregarLibro(Libro libro);
}
