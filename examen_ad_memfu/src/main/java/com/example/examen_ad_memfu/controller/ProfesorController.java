package com.example.examen_ad_memfu.controller;

import com.example.examen_ad_memfu.model.Profesor;
import com.example.examen_ad_memfu.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("profesores")
public class ProfesorController {
    @Autowired
    private ProfesorService profesorService;

    @PostMapping("add")
    public String addProfesor(@RequestBody Profesor profesor){
        profesorService.agregarProf(profesor);
        return "Docente " + profesor.getNombre() + " " + profesor.getApellido() + " añadido correctamente.";
    }

    @GetMapping("getAllProfsByCurso")
    public ResponseEntity<List<Profesor>> getProfesoresByCurso(@RequestParam int idCurso){
        return new ResponseEntity<>(profesorService.findByListaCursosId(idCurso), HttpStatus.OK);
    }
}
