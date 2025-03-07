package com.example.examen_ad_memfu.service;

import com.example.examen_ad_memfu.model.Alumno;

import java.util.List;

public interface AlumnoService {

    void agregarAlumno(Alumno alumno);

    List<Alumno> getAllAlumnos();

    List<Alumno> findByCursoId(int idCurso);

    List<Alumno> findByNombreCurso(String nombreCurso);


}
