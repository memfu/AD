package com.example.LibreriaAPIS.model;

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
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "librerias")
public class Libreria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;
    @Column(name = "nombre_propietario")
    private  String nombreProp;
    @Column
    private String direccion;

    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable (name = "catalogo_librerias",
            joinColumns = @JoinColumn(name = "libreria_id"),
            inverseJoinColumns = @JoinColumn(name = "libro_id"))
    @JsonIgnore
    private List<Libro> coleccionLibros;


    public Libreria(String nombre, String nombreProp, String direccion) {
        this.nombre = nombre;
        this.nombreProp = nombreProp;
        this.direccion = direccion;
    }
}