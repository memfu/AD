package com.example.LibreriaAPIS.repository;

import com.example.LibreriaAPIS.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
    List<Autor> findByNombre(String nombreAutor);

    List<Autor> findByNombreYApellido(String nombre, String apellido);
}
