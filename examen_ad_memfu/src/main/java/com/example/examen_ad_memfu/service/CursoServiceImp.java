package com.example.examen_ad_memfu.service;

import com.example.examen_ad_memfu.model.Curso;
import com.example.examen_ad_memfu.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImp implements CursoService{
    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public List<Curso> findByAulaId(int aula_id) {

        return cursoRepository.findByAulaId(aula_id);
    }

    @Override
    public List<Curso> findByListaProfesoresId(int prof_id) {

        return cursoRepository.findByListaProfesoresId(prof_id);
    }
}
