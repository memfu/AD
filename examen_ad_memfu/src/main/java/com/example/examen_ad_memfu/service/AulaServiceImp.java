package com.example.examen_ad_memfu.service;

import com.example.examen_ad_memfu.model.Aula;
import com.example.examen_ad_memfu.repository.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AulaServiceImp implements AulaService{
    @Autowired
    private AulaRepository aulaRepository;

    @Override
    public List<Aula> findAulaPara31Alumnos() {
        return aulaRepository.findAulasPara31Alumnos();
    }

    @Override
    public List<Aula> findByCapacidadGreaterThan(int capacidad) {
        return aulaRepository.findByCapacidadGreaterThan(30);
    }
}
