package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coche {
    private int id;
    private String marca, modelo, color;
    private int caballos;
    private float precio;

    public Coche(String brand, String model, String color, int cv, float price) {
        this.marca = brand;
        this.modelo = model;
        this.color = color;
        this.caballos = cv;
        this.precio = price;
    }

    @Override
    public String toString() {
        return "ID: " + id + " - Marca: " + marca + ", modelo: " + modelo +
                "\n\t Caballos: " + caballos +
                "\n\t Precio: " + precio;
    }


}
