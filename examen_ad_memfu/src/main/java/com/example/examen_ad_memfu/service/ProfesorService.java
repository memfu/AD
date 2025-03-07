package com.example.examen_ad_memfu.service;

import com.example.examen_ad_memfu.model.Profesor;

import java.util.List;

public interface ProfesorService {
    void agregarProf(Profesor profesor);

    List<Profesor> findByListaCursosId(int idCurso);

}
