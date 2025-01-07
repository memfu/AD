package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pasajero {
    private int id;
    private String nombre;
    private int edad;
    private float peso;
    private int cocheID;

    public Pasajero(String nombre, int edad, float peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
    }

    public Pasajero(String nombre, int edad, float peso, int carId) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.cocheID = carId;
    }

    @Override
    public String toString() {
        return "ID  " + id + " - Nombre: " + nombre +
                "\n\t Edad: " + edad +
                "\n\t Peso: " + peso +
                "\n\t Coche asociado: " + cocheID;
    }

}
