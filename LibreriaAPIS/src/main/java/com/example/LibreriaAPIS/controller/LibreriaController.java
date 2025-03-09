package com.example.LibreriaAPIS.controller;

import com.example.LibreriaAPIS.model.Libreria;
import com.example.LibreriaAPIS.service.LibreriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("librerias")
public class LibreriaController {

    @Autowired
    private LibreriaService libreriaService;

    @PostMapping("add")
    public Libreria addLibrerias(@RequestBody Libreria libreria) {
        libreriaService.agregarLibreria(libreria);
        return libreria;
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Libreria>> getLibrerias(){
        return new ResponseEntity<>(libreriaService.getAllLibreria(), HttpStatus.OK);
    }
}
