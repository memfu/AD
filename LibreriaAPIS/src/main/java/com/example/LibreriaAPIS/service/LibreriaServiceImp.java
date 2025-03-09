package com.example.LibreriaAPIS.service;

import com.example.LibreriaAPIS.model.Libreria;
import com.example.LibreriaAPIS.repository.LibreriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibreriaServiceImp implements LibreriaService {

    @Autowired
    private LibreriaRepository libreriaRepository;
    @Override
    public void agregarLibreria(Libreria libreria) {
        libreriaRepository.save(libreria);
    }

    @Override
    public List<Libreria> getAllLibreria() {
        return libreriaRepository.findAll();
    }

    @Override
    public List<Libreria> findByNombre(String nombre) {
        return libreriaRepository.findByNombre(nombre);
    }
}
