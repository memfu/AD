package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pasajero implements Serializable {
    /*
    No se necesita el serialVersionUID porque ya no se transforma el objeto en datos
    private static final long serialVersionUID = 20250107L;
     */

    @Id // indica que es la PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //indica que es un elemento generado automáticamente por la BBDD y lo autoincrementa
    private int id;

    @Column
    private String nombre;
    @Column
    private int edad;
    @Column
    private float peso;
    @Column(name = "cocheAsignado")
    private int cocheID;

    public Pasajero(String nombre, int edad, float peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
    }


    @Override
    public String toString() {
        return "ID  " + id + " - Nombre: " + nombre +
                "\n\t Edad: " + edad +
                "\n\t Peso: " + peso +
                "\n\t Coche asociado: " + cocheID;
    }

}
