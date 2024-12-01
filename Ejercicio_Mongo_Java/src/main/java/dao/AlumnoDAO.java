package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
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
        System.out.println("Se ha insertado correctamente el/la estudiante " + alumno.getNombre() + " con correo electrónico " + alumno.getCorreo() + ".");
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

    public void deleteAlumnoAprobado (int notaAprobado) {
        Bson filtradoNota = Filters.gte(DBScheme.keyCalifAl, notaAprobado);
        DeleteResult resultado =  collection.deleteMany(filtradoNota);
        // Obtiene el número de documentos eliminados
        long eliminados = resultado.getDeletedCount();
        if (eliminados == 0) {
            System.out.println("No se han hecho cambios.");
        } else {
            System.out.println("Se han dado de baja correctamente a " + eliminados + " alumnos aprobados.");
        }
    }

    public void showAlumnos(){
        // Cuenta el número total de alumnos en la colección
        long totalAlumnos = collection.countDocuments();
        System.out.println("Se han encontrado " + totalAlumnos + " estudiantes.");

        FindIterable<Alumno> allStudents = collection.find();
        MongoCursor<Alumno> cursor = allStudents.cursor();
        while (cursor.hasNext()){
            alumno = cursor.next();
            System.out.println(alumno.toString());
        }
    }

}
