package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Coche implements Serializable {
    private final static long serialVersionUID = 221024L;

    // Atributos
    private int id;
    private String matricula;
    private String marca;
    private String modelo;
    private String color;

    @Override
    public String toString() {
        return "Id: " + id + " - Coche de la marca " + marca + ", modelo " + modelo + ". De color " + color + " y con matrícula " + matricula + ".";
    }
}
