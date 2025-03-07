package com.example.examen_ad_memfu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Curso implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_curso")
    private int id;

    @Column
    private String nombre;

    @OneToOne
    @JoinColumn(name = "id_aula")
    @JsonIgnore
    private Aula aula;

    // ℹ️ El atributo de id_profesor lo he obviado, porque tiene una relación de con curso de N:M y no 1:N

    @OneToMany(mappedBy = "curso", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("curso") // Evita el ciclo inverso
    private List<Alumno> listaAlumnos;

    @ManyToMany(mappedBy = "listaCursos", fetch = FetchType.EAGER)
    @JsonIgnore // Para que no salga cuando obtengo todos los alumnos
    private List<Profesor> listaProfesores;
}
