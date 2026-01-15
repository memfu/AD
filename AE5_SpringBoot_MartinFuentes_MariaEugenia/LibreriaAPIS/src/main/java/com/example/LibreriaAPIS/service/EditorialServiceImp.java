package com.example.LibreriaAPIS.service;

import com.example.LibreriaAPIS.model.Editorial;
import com.example.LibreriaAPIS.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditorialServiceImp implements EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;
    @Override
    public void agregarEditorial(Editorial editorial) {
        editorialRepository.save(editorial);
    }

    @Override
    public List<Editorial> getAllEditoriales() {
        return editorialRepository.findAll();
    }
}
