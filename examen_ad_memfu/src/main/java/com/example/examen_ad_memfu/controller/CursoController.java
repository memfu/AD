package com.example.examen_ad_memfu.controller;

import com.example.examen_ad_memfu.model.Curso;
import com.example.examen_ad_memfu.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping("getCursoByAulaId")
    public ResponseEntity<List<Curso>> getCursoByAulaId(@RequestParam int aula_id) {
        return new ResponseEntity<>(cursoService.findByAulaId(aula_id), HttpStatus.OK);
    }

    @GetMapping("getCursosByProfId")
    public ResponseEntity<List<Curso>> getCursosByProfId(@RequestParam int prof_id){
        return new ResponseEntity<>(cursoService.findByListaProfesoresId(prof_id),HttpStatus.OK);
    }
}
