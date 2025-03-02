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
@Table(name = "cursos")
public class Curso implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private  String nombre;
    @Column(name = "fecha_inicio")
    private LocalDate fechaInic;
    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    // Para la tabla renacida
    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable (name = "cursos_participantes",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "madre_id"))
    private List<Madre> madresApuntadas;

    public Curso(String nombre, LocalDate fechaInic, LocalDate fechaFin) {
        this.nombre = nombre;
        this.fechaInic = fechaInic;
        this.fechaFin = fechaFin;
    }
}
