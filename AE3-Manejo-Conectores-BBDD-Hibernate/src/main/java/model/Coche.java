package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "coches")
public class Coche implements Serializable {
    /*
    No se necesita el serialVersionUID porque ya no se transforma el objeto en datos
    private static final long serialVersionUID = 20250107L;
     */

    @Id // indica que es la PK
    @GeneratedValue (strategy = GenerationType.IDENTITY) //indica que es un elemento generado automáticamente por la BBDD y lo autoincrementa
    private int id;

    @Column
    private String marca;
    @Column
    private String modelo;
    @Column
    private String color;
    @Column
    private int caballos;
    @Column
    private float precio;
    @Column(name = "nrPasajeros")
    private int nrPass;

    public Coche(String brand, String model, String color, int cv, float price) {
        this.marca = brand;
        this.modelo = model;
        this.color = color;
        this.caballos = cv;
        this.precio = price;
    }

    public Coche(String brand, String model, String color, int cv, float price, int nrPass) {
        this.marca = brand;
        this.modelo = model;
        this.color = color;
        this.caballos = cv;
        this.precio = price;
        this.nrPass = nrPass;
    }

    @Override
    public String toString() {
        return "ID: " + id + " - Marca: " + marca + ", modelo: " + modelo +
                "\n\t Color: " + color +
                "\n\t Caballos: " + caballos +
                "\n\t Precio: " + precio +
                "\n\t Nr. de pasajeros: " + nrPass;
    }


}
