package com.example.examen_ad_memfu.service;

import com.example.examen_ad_memfu.model.Curso;

import java.util.List;

public interface CursoService {
    List<Curso> findByAulaId(int aula_id);

    List<Curso> findByListaProfesoresId(int prof_id);
}
