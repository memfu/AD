package model;

import database.DBScheme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profesor extends Usuario {
    @BsonProperty(DBScheme.keyTitlePr)
    private String titulo;
    @BsonProperty(DBScheme.keySubjectsPr) // To indicate that the name of the property within the collection has another name
    private ArrayList<String> asignaturas;


    public Profesor(String nombre, int edad, float rating, String genero, String correo, String telefono, String titulo, ArrayList<String> asignaturas) {
        super(nombre, edad, rating, genero, correo, telefono); // Llamada al constructor de Usuario
        this.titulo = titulo;
        this.asignaturas = asignaturas;
    }

    public Profesor(Usuario usuario, String titulo, ArrayList<String> asignaturas) {
        super(usuario.getNombre(), usuario.getEdad(), usuario.getRating(), usuario.getGenero(), usuario.getCorreo(), usuario.getTelefono()); // Llamada al constructor de Usuario
        this.titulo = titulo;
        this.asignaturas = asignaturas;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\t Lista de asignaturas: " + getAsignaturas() +
                "\n\t Título: " + getTitulo()
                ;
    }
}
