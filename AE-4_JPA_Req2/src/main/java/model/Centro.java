package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "centros")
public class Centro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private  String nombre;
    @Column(name = "nombre_propietario")
    private  String nombreProp;
    @Column
    private String direccion;

    //Bidireccionalidad con la entidad Madre -> busca en la clase "Madre", el atributo "centro"
    @OneToMany(mappedBy = "centro", fetch = FetchType.EAGER)
    private List<Madre> madresApuntadas;

    public Centro(String nombre, String nombreProp, String direccion) {
        this.nombre = nombre;
        this.nombreProp = nombreProp;
        this.direccion = direccion;
    }
}
