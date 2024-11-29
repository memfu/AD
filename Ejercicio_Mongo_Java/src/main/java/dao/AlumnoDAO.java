package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import database.DBScheme;
import database.MongoDBConnection;
import model.Alumno;
import model.Usuario;
import org.bson.conversions.Bson;

public class AlumnoDAO {
    Alumno alumno;
    MongoCollection collection = new MongoDBConnection().getCollection("alumnos");


    public void addAlumno(Usuario usuarioGeneral, String grado, int calificacion) {
        alumno = new Alumno(usuarioGeneral,grado,calificacion);
        collection.insertOne(alumno);
    }

    public void searchAlumnoByMail(String email) {
        Bson filtradoMail = Filters.eq(DBScheme.keyMail, email);
        FindIterable<Alumno> iterable = collection.find(filtradoMail);
        MongoCursor<Alumno> cursor = iterable.cursor();
        if (cursor.hasNext()){
            alumno = cursor.next();
            System.out.println(alumno.toString());
        } else {
            System.out.println("No hay ningún estudiante con ese correo electrónico.");
        }
    }

    public void updateAlumno(String campo) {

    }

    public void showAlumnos(){
        FindIterable<Alumno> allStudents = collection.find();
        MongoCursor<Alumno> cursor = allStudents.cursor();
        while (cursor.hasNext()){
            alumno = cursor.next();
            System.out.println(alumno.toString());
        }
    }

}
