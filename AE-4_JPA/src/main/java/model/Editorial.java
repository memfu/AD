package model;

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
@Table
public class Editorial implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private  String nombre;
    @Column
    private String direccion;

    //Bidireccionalidad con la entidad Libro -> busca en la clase "Libro", el atributo "editorial" para conseguir todos los libros con la editorial
    @OneToMany(mappedBy = "editorial", fetch = FetchType.EAGER)
    private List<Libro> librosPublicados;

    public Editorial(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }


}
