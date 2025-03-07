package com.example.examen_ad_memfu.service;

import com.example.examen_ad_memfu.model.Aula;

import java.util.List;

public interface AulaService {
    List<Aula> findAulaPara31Alumnos();

    List<Aula> findByCapacidadGreaterThan(int capacidad);

}
