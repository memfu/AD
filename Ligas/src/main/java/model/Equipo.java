package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@NamedQuery(name = "Equipo.findAll", query = "FROM Equipo")
@NamedQuery(name = "Equipo.findAllByNombre", query = "FROM Equipo e WHERE e.nombre=:parametro")
@NamedQuery(name = "Equipo.findAllByCiudad", query = "FROM Equipo e WHERE e.ciudad=:parametro")

@Entity
@Table(name = "equipos")
public class Equipo  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column (nullable = false)
    private String nombre;
    @Column (nullable = false)
    private String ciudad;

    // unidireccional
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_liga")
    private Liga liga;

    // Se hace donde está la FK en la tabla
    @OneToOne (cascade = CascadeType.ALL) //si hago modificacion lo replico en cascada
    @JoinColumn(name = "id_entrenador")
    private Entrenador entrenador;

    // bidireccionalidad
    @OneToMany(mappedBy = "equipo")
    private List<Jugador> jugadoresList;


    public Equipo(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
    }
}
