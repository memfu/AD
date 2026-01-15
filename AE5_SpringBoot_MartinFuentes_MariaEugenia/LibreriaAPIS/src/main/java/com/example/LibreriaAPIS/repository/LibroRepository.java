package com.example.LibreriaAPIS.repository;

import com.example.LibreriaAPIS.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//tantos métodos adicionales como necesitemos
//los métodos por defecto me los da el JpaRepository (persist, save, merge, list, get-> by Id)
public interface LibroRepository extends JpaRepository<Libro, Integer> {

    List<Libro> findByTitulo(String titulo);
    @Query("SELECT l FROM Libro l WHERE l.autor.nombre = :nombre AND l.autor.apellidos = :apellidos")
    List<Libro> findByAutor(@Param("nombre") String nombre, @Param("apellidos") String apellidos);

    List<Libro> findByListaLibrerias_Nombre(String nombreLibreria);

    List<Libro> findByEditorial_Id(int idEditorial);
}
