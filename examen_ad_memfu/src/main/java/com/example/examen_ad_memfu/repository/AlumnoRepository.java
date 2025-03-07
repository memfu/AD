package com.example.examen_ad_memfu.repository;

import com.example.examen_ad_memfu.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
    // Para buscar por id del curso
    List<Alumno> findByCursoId (int idCurso);

    // Para buscar por nombre del curso
    @Query("FROM Alumno a WHERE a.curso.nombre= :nombreCurso")
    List<Alumno> findByNombreCurso(@Param("nombreCurso") String nombreCurso);
    // Esta la hice aparte porque me parecía más lógico buscar por nombre que por id

}
