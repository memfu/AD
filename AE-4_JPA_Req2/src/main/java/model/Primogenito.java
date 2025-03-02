package model;

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
@Table(name = "primogenitos")
public class Primogenito implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private  String nombre;
    @Column
    private String apellidos;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNac;
    // Un hijo solo puede tener una madre
    // Una madre solo puede tener un hijo (madres primerizas de solo un hijo)
    @OneToOne
    @JoinColumn(name = "madre_id", unique = true) // La FK de Madre en Hijo
    private Madre madre;

    public Primogenito(String nombre, String apellidos, LocalDate fechaNac, Madre madre) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.madre = madre;
    }
    public Primogenito(String nombre, String apellidos, LocalDate fechaNac) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
    }
}
