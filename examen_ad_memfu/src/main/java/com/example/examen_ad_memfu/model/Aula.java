package com.example.examen_ad_memfu.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Aula implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_aula")
    private int id;

    @Column
    private String nombre;

    @Column
    private int capacidad;

    @OneToOne(mappedBy = "aula")
    @JsonBackReference
    private Curso curso;
}
