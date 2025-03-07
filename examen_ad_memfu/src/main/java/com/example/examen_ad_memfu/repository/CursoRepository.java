package com.example.examen_ad_memfu.repository;

import com.example.examen_ad_memfu.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository  extends JpaRepository<Curso, Integer> {
    List<Curso> findByAulaId(int aula_id);

    List<Curso> findByListaProfesoresId(int profesor_id);
}
