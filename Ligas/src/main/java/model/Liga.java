package model;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@NamedQuery(name = "Liga.findAll", query = "FROM Liga")
@NamedQuery(name = "Liga.findAllByNombre", query = "FROM Liga l WHERE l.nombre=:parametro")
@NamedQuery(name = "Liga.findAllByMonthStart", query = "FROM Liga l WHERE MONTH(l.fecha_inicio)=:parametro")
@NamedQuery(name = "Liga.findAllByMonthEnd", query = "FROM Liga l WHERE MONTH(l.fecha_final)=:parametro")
@NamedQuery(name = "Liga.findAllByYear", query = "FROM Liga l WHERE YEAR(l.fecha_final)=:parametro")

@Entity
@Table(name = "ligas")
public class Liga implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column (nullable = false)
    private String nombre;
    @Column
    private Date fecha_inicio;
    @Column(name = "fecha_fin")
    private Date fecha_final;

    // bidireccionalidad (nunca es obligatoria, porque no existe la FK)
    @OneToMany(mappedBy = "liga")
    private List<Equipo> equipos;


    public Liga(String nombre) {
        this.nombre = nombre;
    }

    public Liga(String nombre, Date fecha_inicio, Date fecha_final) {
        this.nombre = nombre;
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
    }
}
