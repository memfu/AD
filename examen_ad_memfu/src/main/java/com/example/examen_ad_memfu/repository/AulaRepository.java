package com.example.examen_ad_memfu.repository;

import com.example.examen_ad_memfu.model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AulaRepository  extends JpaRepository<Aula, Integer> {
    @Query("FROM Aula a WHERE a.capacidad > 30")
    List<Aula> findAulasPara31Alumnos();
    // Aquí he hecho dos métodos: uno es fijo y el otro se puede variar la cantidad. También para usar un @Query

    List<Aula> findByCapacidadGreaterThan(int capacidad);

}
