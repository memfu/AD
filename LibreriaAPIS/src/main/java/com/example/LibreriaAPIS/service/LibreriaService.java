package com.example.LibreriaAPIS.service;

import com.example.LibreriaAPIS.model.Libreria;

import java.util.List;

public interface LibreriaService {
    void agregarLibreria(Libreria libreria);

    List<Libreria> getAllLibreria();

    List<Libreria> findByNombre(String nombre);
}
