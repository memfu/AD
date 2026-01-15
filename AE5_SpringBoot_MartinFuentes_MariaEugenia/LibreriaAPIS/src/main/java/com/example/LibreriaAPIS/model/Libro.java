package com.example.LibreriaAPIS.model;

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
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "libros")
public class Libro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String titulo;
    @Column
    private double precio;

    // un libro tiene una editorial, una editorial puede tener muchos libros
    // Cascada: si hago aquí una modificación, la replico en cascada a todos aquellos objetos con los que esté relacionado
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "editorial_id")
    @JsonIgnoreProperties("librosPublicados")
    private Editorial editorial;

    // un libro tiene un autor, un autor puede tener muchos libros
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    @JsonIgnoreProperties("librosEscritos")
    private Autor autor;

    @ManyToMany(mappedBy = "coleccionLibros", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Libreria> listaLibrerias;

    public Libro(String titulo, double precio) {
        this.titulo = titulo;
        this.precio = precio;
    }

    public Libro(String titulo, double precio, Editorial editorial, Autor autor) {
        this.titulo = titulo;
        this.precio = precio;
        this.editorial = editorial;
        this.autor = autor;
    }
}
