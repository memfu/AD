package com.example.LibreriaAPIS.service;

import com.example.LibreriaAPIS.model.Libro;
import com.example.LibreriaAPIS.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// implementación del servicio, la lógica contra la BBDD
@Service
public class LibroServiceImp implements LibroService{
    @Autowired // para inicializar el repository sin que me meta todos los métodos
    private LibroRepository libroRepository;
    @Override
    public Libro agregarLibro(Libro libro) {
        // quiero que se pueda agregar
        // se necesita el repository para poder ejecutar los métodos contra la BBDD
        libroRepository.save(libro);
        return null;
    }
}
