package com.example.LibreriaAPIS.repository;

import com.example.LibreriaAPIS.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

//tantos métodos adicionales como necesitemos
//los métodos por defecto me los da el JpaRepository (persist, save, merge, list, get-> by Id)
public interface LibroRepository extends JpaRepository<Libro, Integer> {
}
