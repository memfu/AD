package com.example.LibreriaAPIS.repository;

import com.example.LibreriaAPIS.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
    List<Autor> findByNombre(String nombreAutor);

    List<Autor> findByNombreAndApellidos(String nombre, String apellidos);
}
