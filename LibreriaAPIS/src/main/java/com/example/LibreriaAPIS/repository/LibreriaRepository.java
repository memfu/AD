package com.example.LibreriaAPIS.repository;

import com.example.LibreriaAPIS.model.Libreria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibreriaRepository extends JpaRepository<Libreria, Integer> {
    List<Libreria> findByNombre(String nombre);

}
