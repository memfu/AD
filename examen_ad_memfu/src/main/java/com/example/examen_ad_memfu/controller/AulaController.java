package com.example.examen_ad_memfu.controller;

import com.example.examen_ad_memfu.model.Aula;
import com.example.examen_ad_memfu.service.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("aulas")
public class AulaController {
    @Autowired
    private AulaService aulaService;

    @GetMapping("getAulas>30")
    public ResponseEntity<List<Aula>> getAulasPara31Alumnos(){
        return new ResponseEntity<>(aulaService.findAulaPara31Alumnos(), HttpStatus.OK);
    }

    @GetMapping("getAulasByCapacidad")
    public ResponseEntity<List<Aula>> getAulasPorCapacidad(){
        return new ResponseEntity<>(aulaService.findByCapacidadGreaterThan(30),HttpStatus.OK);
    }

}
