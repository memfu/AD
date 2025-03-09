package com.example.LibreriaAPIS.repository;

import com.example.LibreriaAPIS.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//tantos métodos adicionales como necesitemos
//los métodos por defecto me los da el JpaRepository (persist, save, merge, list, get-> by Id)
public interface LibroRepository extends JpaRepository<Libro, Integer> {

    List<Libro> findByTitulo(String titulo);

    List<Libro> findByAutor_Nombre(String nombreAutor);

    List<Libro> findByListaLibrerias_Nombre(String nombreLibreria);

    List<Libro> findByEditorial_Id(int idEditorial);
}
