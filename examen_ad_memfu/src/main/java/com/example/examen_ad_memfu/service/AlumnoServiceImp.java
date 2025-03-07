package com.example.examen_ad_memfu.service;

import com.example.examen_ad_memfu.model.Alumno;
import com.example.examen_ad_memfu.model.Curso;
import com.example.examen_ad_memfu.repository.AlumnoRepository;
import com.example.examen_ad_memfu.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImp implements AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public void agregarAlumno(Alumno alumno) {
        // Si el curso que se ha metido en el alumno no es null ni 0
        if (alumno.getCurso() != null && alumno.getCurso().getId() != 0) {
            // Comprueba si existe el curso en la bbdd comparando los id
            if (!cursoRepository.existsById(alumno.getCurso().getId())) {
                System.out.println("Curso no encontrado, el alumno no será guardado.");
                return; // Sale del método sin guardar el alumno
            }

            // Si el curso existe, lo obtiene y lo asigna al alumno
            Curso cursoExistente = cursoRepository.findById(alumno.getCurso().getId()).get();
            alumno.setCurso(cursoExistente);
        } else {
            alumno.setCurso(null); // Asegura que no se intente guardar el id de un curso que no existe un curso nuevo
        }
        alumnoRepository.save(alumno);
    }

    @Override
    public List<Alumno> getAllAlumnos() {
        return alumnoRepository.findAll();
    }

    @Override
    public List<Alumno> findByCursoId(int idCurso) {
        return alumnoRepository.findByCursoId(idCurso);
    }
    @Override
    public List<Alumno> findByNombreCurso(String nombreCurso) {
        return alumnoRepository.findByNombreCurso(nombreCurso);
    }
}
