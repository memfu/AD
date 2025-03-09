package com.example.LibreriaAPIS.service;

import com.example.LibreriaAPIS.model.Autor;

import java.util.List;

public interface AutorService {
    void agregarAutor(Autor autor);

    List<Autor> getAllAutores();

    List<Autor> findByNombre(String nombre);

    List<Autor> findByNombreAndApellidos(String nombre, String apellidos);

}
