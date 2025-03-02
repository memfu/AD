package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "madres")
public class Madre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private  String nombre;
    @Column
    private String apellidos;
    @Column
    private String direccion;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNac;

    // Una madre solo puede estar matriculada en un centro, pero un centro puede tener varias madres
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "centro_id")
    private Centro centro;

    @ManyToMany(mappedBy = "madresApuntadas", fetch = FetchType.EAGER)
    private List<Curso> listaCursos;

    public Madre(String nombre, String apellidos, String direccion, LocalDate fechaNac, Centro centro) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.fechaNac = fechaNac;
        this.centro = centro;
    }
    public Madre(String nombre, String apellidos, String direccion, LocalDate fechaNac) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.fechaNac = fechaNac;
    }

}
