package com.example.LibreriaAPIS.controller;

import com.example.LibreriaAPIS.model.Editorial;
import com.example.LibreriaAPIS.service.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("editoriales")
public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    @PostMapping("add")
    public Editorial addEditorial(@RequestBody Editorial editorial) {
        editorialService.agregarEditorial(editorial);
        return editorial;
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Editorial>> getEditoriales(){
        return new ResponseEntity<>(editorialService.getAllEditoriales(), HttpStatus.OK);
    }


}
