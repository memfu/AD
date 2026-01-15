package com.example.LibreriaAPIS.controller;

import com.example.LibreriaAPIS.model.Libro;
import com.example.LibreriaAPIS.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "libros", produces = MediaType.APPLICATION_JSON_VALUE)
public class LibroController {

    @Autowired
    private LibroService libroService;

    @PostMapping(value ="add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Libro addAlumno(@RequestBody Libro libro) {
        libroService.agregarLibro(libro);
        return libro;
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Libro>> getLibros(){
        return new ResponseEntity<>(libroService.getAllLibros(), HttpStatus.OK);
    }

    @GetMapping("findAllLibrosByTitulo")
    public ResponseEntity<List<Libro>> getLibrosByTitulo(@RequestParam String titulo){
        return new ResponseEntity<>(libroService.findByTitulo(titulo), HttpStatus.OK);
    }

    @GetMapping("findAllLibrosByAutor")
    public ResponseEntity<List<Libro>> getByNombreAutor(@RequestParam String nombreAutor, @RequestParam String apellidosAutor){
        return new ResponseEntity<>(libroService.findByAutor(nombreAutor, apellidosAutor), HttpStatus.OK);
    }

    @GetMapping("findAllLibrosByLibreria")
    public ResponseEntity<List<Libro>> getByLibreria(@RequestParam String nombreLibreria){
        return new ResponseEntity<>(libroService.findByListaLibrerias_Nombre(nombreLibreria), HttpStatus.OK);
    }

    @GetMapping("findAllLibrosByEditorialId")
    public ResponseEntity<List<Libro>> getByEditorialId(@RequestParam int idEditorial){
        return new ResponseEntity<>(libroService.findByEditorial_Id(idEditorial), HttpStatus.OK);
    }

}
