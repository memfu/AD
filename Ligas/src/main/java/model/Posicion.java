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

@NamedQuery(name = "Posicion.findAll", query = "FROM Posicion")
@NamedQuery(name = "Posicion.findAllByNombre", query = "FROM Posicion p WHERE p.nombre=:parametro")

@Entity
@Table(name = "posiciones")
public class Posicion implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String posicion;
    @OneToMany(mappedBy = "posicion")
    private List<Jugador> jugadoresList;

}
