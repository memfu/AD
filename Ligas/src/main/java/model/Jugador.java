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

@NamedQuery(name = "Jugador.findAll", query = "FROM Jugador")
@NamedQuery(name = "Jugador.findAllByNombre", query = "FROM Jugador j WHERE j.nombre=:parametro")
@NamedQuery(name = "Jugador.findAllByValor", query = "FROM Jugador j WHERE j.valor=:parametro")
@NamedQuery(name = "Jugador.findAllByNacionalidad", query = "FROM Jugador j WHERE j.nacionalidad=:parametro")
@NamedQuery(name = "Jugador.findAllByGoles", query = "FROM Jugador j WHERE j.goles=:parametro")

@Entity
@Table (name = "jugadores")
public class Jugador implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column (nullable = false)
    private String nombre;
    @Column(name = "valor_mercado", nullable = false)
    private int valor;
    @Column (nullable = false)
    private String nacionalidad;
    @Column (nullable = false)
    private int goles;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "id_posicion")
    private Posicion posicion;


    public Jugador(String nombre, int valor, String nacionalidad, int goles) {
        this.nombre = nombre;
        this.valor = valor;
        this.nacionalidad = nacionalidad;
        this.goles = goles;
    }

    public Jugador(String nombre, int valor, String nacionalidad) {
        this.nombre = nombre;
        this.valor = valor;
        this.nacionalidad = nacionalidad;
    }
}
