package com.example.examen_ad_memfu.service;

import com.example.examen_ad_memfu.model.Profesor;
import com.example.examen_ad_memfu.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImp implements ProfesorService{
    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    public void agregarProf(Profesor profesor) {
        profesorRepository.save(profesor);
    }

    @Override
    public List<Profesor> findByListaCursosId(int idCurso) {
        return profesorRepository.findByListaCursosId(idCurso);
    }
}
