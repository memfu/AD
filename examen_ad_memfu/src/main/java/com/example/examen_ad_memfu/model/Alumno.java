package com.example.examen_ad_memfu.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Alumno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_alumno")
    private int id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column (name = "fecha_nacimiento")
    private LocalDate fechaNac;

    @Column
    private String email;

    // un alumno tiene un curso, un curso puede tener muchos alumnos
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_curso")
    @JsonIgnoreProperties("listaAlumnos") // Evita el bucle sin ocultar la relación
    private Curso curso;
}
