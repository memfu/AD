package com.example.LibreriaAPIS.service;

import com.example.LibreriaAPIS.model.Autor;
import com.example.LibreriaAPIS.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorServiceImp implements AutorService{

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public void agregarAutor(Autor autor) {
        autorRepository.save(autor);
    }

    @Override
    public List<Autor> getAllAutores() {
        return autorRepository.findAll();
    }


    @Override
    public List<Autor> findByNombre(String nombreAutor) {
        return autorRepository.findByNombre(nombreAutor);
    }

    @Override
    public List<Autor> findByNombreAndApellidos(String nombre, String apellidos) {
        return autorRepository.findByNombreAndApellidos(nombre, apellidos);
    }
}
