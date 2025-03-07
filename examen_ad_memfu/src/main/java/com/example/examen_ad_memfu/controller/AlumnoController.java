package com.example.examen_ad_memfu.controller;

import com.example.examen_ad_memfu.model.Alumno;
import com.example.examen_ad_memfu.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    @PostMapping(value ="add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Alumno addAlumno(@RequestBody Alumno alumno){
        alumnoService.agregarAlumno(alumno);
        return alumno;
        // si el id del curso no existe muestra null en nombre y en la consola el mensaje de que no se ha añadido al alumno
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Alumno>> getAlumnos(){
        return new ResponseEntity<>(alumnoService.getAllAlumnos(), HttpStatus.OK);
    }

    @GetMapping("findAllAlumnosByCurso")
    public ResponseEntity<List<Alumno>> getAlumnosByCurso(@RequestParam int idCurso){
        return new ResponseEntity<>(alumnoService.findByCursoId(idCurso), HttpStatus.OK);
    }

    @GetMapping("findAllAlumnosByNombreCurso")
    public ResponseEntity<List<Alumno>> getAlumnosByNombreCurso(@RequestParam String nombreCurso){
        return new ResponseEntity<>(alumnoService.findByNombreCurso(nombreCurso), HttpStatus.OK);
    }

}
