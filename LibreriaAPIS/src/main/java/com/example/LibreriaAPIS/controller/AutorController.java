package com.example.LibreriaAPIS.controller;

import com.example.LibreriaAPIS.model.Autor;
import com.example.LibreriaAPIS.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping("add")
    public Autor addAutor(@RequestBody Autor autor) {
        autorService.agregarAutor(autor);
        return autor;
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Autor>> getAutores(){
        return new ResponseEntity<>(autorService.getAllAutores(), HttpStatus.OK);
    }

    @GetMapping("findAutorByNombre")
    public ResponseEntity<List<Autor>> getByNombre(@RequestParam String nombre){
        return new ResponseEntity<>(autorService.findByNombre(nombre), HttpStatus.OK);
    }

    @GetMapping("findAutorByNombreCompleto")
    public ResponseEntity<List<Autor>> getByNombreCompleto(@RequestParam String nombre, @RequestParam String apellidos){
        return new ResponseEntity<>(autorService.findByNombreAndApellidos(nombre, apellidos), HttpStatus.OK);
    }


}
