package com.example.LibreriaAPIS.service;

import com.example.LibreriaAPIS.model.Editorial;

import java.util.List;

public interface EditorialService {

    void agregarEditorial(Editorial editorial);

    List<Editorial> getAllEditoriales();
}
