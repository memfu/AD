package com.example.examen_ad_memfu.repository;

import com.example.examen_ad_memfu.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfesorRepository  extends JpaRepository<Profesor, Integer> {
    // Para buscar por id del curso
    List<Profesor> findByListaCursosId(int idCurso);
}
