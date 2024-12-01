package model;

import database.DBScheme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alumno extends Usuario{

    @BsonProperty(DBScheme.keyGradeAl)
    private String grade;
    @BsonProperty("calificaation")
    private int calificacion;


    public Alumno(String nombre, int edad, float rating, String genero, String correo, String telefono, String grade, int calificacion) {
        super(nombre, edad, rating, genero, correo, telefono);
        this.calificacion = calificacion;
        this.grade = grade;
    }

    public Alumno(Usuario usuario, String grade, int calificacion) {
        super(usuario.getNombre(), usuario.getEdad(), usuario.getRating(), usuario.getGenero(), usuario.getCorreo(), usuario.getTelefono()); // Llamada al constructor de Usuario
        this.grade = grade;
        this.calificacion = calificacion;
    }



    @Override
    public String toString() {
        return super.toString() +
                "\n\t Calificación: " + getCalificacion() +
                "\n\t Grado: " + getGrade()
                ;
    }
}
