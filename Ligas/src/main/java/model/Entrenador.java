package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@NamedQuery(name = "Entrenador.findAll", query = "FROM Entrenador")
@NamedQuery(name = "Entrenador.findAllByNombre", query = "FROM Entrenador e WHERE e.nombre=:parametro")
@NamedQuery(name = "Entrenador.findAllByCalificacion", query = "FROM Entrenador e WHERE e.calificacion=:parametro")
@NamedQuery(name = "Entrenador.findAllByTitulos", query = "FROM Entrenador e WHERE e.titulos=:parametro")


@Entity
@Table(name = "entrenadores")
public class Entrenador  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column (nullable = false)
    private String nombre;
    @Column (nullable = false)
    private int calificacion;
    @Column (nullable = false)
    private int titulos;

    // bidireccionalidad
    @OneToOne(mappedBy = "entrenador") // nombre del atributo donde está el joincolumn
    private Equipo equipo;

    public Entrenador(String nombre, int calificacion, int titulos) {
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.titulos = titulos;
    }

    public Entrenador(String nombre) {
        this.nombre = nombre;
    }
}
