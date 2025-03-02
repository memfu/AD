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
@Table (name = "autores")
public class Autor implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private  String nombre;
    @Column
    private String apellidos;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNac;

    //Bidireccionalidad con la entidad Libro -> busca en la clase "Libro", el atributo "autor" para conseguir todos los libros escritos por el autor
    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    private List<Libro> librosEscritos;

    public Autor(String nombre, String apellidos, LocalDate fechaNac) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
    }
}
