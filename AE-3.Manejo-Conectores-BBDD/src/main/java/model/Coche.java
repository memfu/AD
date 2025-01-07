package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coche implements Serializable {
    private static final long serialVersionUID = 20250107L;

    private int id;
    private String marca, modelo, color;
    private int caballos;
    private float precio;
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
