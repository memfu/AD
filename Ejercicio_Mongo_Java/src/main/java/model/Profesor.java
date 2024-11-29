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
public class Profesor {
    @BsonProperty(DBScheme.keyTitlePr)
    private String titulo;
    @BsonProperty(DBScheme.keySubjectsPr) // To indicate that the name of the property within the collection has another name
    private ArrayList<String> asignaturas;


    public Profesor(Usuario usuario, String titulo, ArrayList<String> asignaturas) {
        this.asignaturas = asignaturas;
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\t Lista de asignaturas: " + getAsignaturas() +
                "\n\t Título: " + getTitulo()
                ;
    }
}
