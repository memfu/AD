package com.example.LibreriaAPIS.service;

import com.example.LibreriaAPIS.model.Libro;
import com.example.LibreriaAPIS.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// implementación del servicio, la lógica contra la BBDD
@Service
public class LibroServiceImp implements LibroService{
    @Autowired // para inicializar el repository sin que me meta todos los métodos
    private LibroRepository libroRepository;
    @Override
    public void agregarLibro(Libro libro) {
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
    public List<Libro> findByAutor_Nombre(String nombreAutor) {
        return libroRepository.findByAutor_Nombre(nombreAutor);
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
