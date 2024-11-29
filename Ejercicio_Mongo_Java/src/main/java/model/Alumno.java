package model;

import dao.AlumnoDAO;
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
    @BsonProperty(DBScheme.keyCalifAl)
    private int calificacion;


    public Alumno(Usuario usuario, String grade, int calificacion) {
        this.calificacion = calificacion;
        this.grade = grade;
    }


    @Override
    public String toString() {
        return super.toString() +
                "\n\t Calificación: " + getCalificacion() +
                "\n\t Grado: " + getGrade()
                ;
    }
}
