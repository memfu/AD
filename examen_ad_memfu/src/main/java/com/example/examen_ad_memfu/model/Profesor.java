package com.example.examen_ad_memfu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Profesor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_profesor")
    private int id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String email;

    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable (name = "Profesor_Curso",
            joinColumns = @JoinColumn(name = "id_profesor"),
            inverseJoinColumns = @JoinColumn(name = "id_curso"))
    @JsonIgnore
    private List<Curso> listaCursos;
}
