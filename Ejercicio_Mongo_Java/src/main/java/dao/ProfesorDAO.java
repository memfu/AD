package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import database.DBScheme;
import database.MongoDBConnection;
import model.Profesor;
import model.Usuario;
import org.bson.conversions.Bson;

import java.util.ArrayList;

public class ProfesorDAO {
    Profesor profesor;
    MongoCollection collection = new MongoDBConnection().getCollection("profesores");


    public void addProf(Usuario usuarioGeneral, String titulo, ArrayList<String> asignaturas) {
        profesor = new Profesor(usuarioGeneral,titulo,asignaturas);
        collection.insertOne(profesor);
        System.out.println("Se ha insertado correctamente el/la docente " + profesor.getNombre() + " con correo electrónico " + profesor.getCorreo() + ".");

    }

    public void searchProfByAge(int minAge, int maxAge) {
        Bson filtroEdad = Filters.and(Filters.gte(DBScheme.keyAge, minAge), Filters.lte(DBScheme.keyAge,maxAge));
        FindIterable<Profesor> profesEdad = collection.find(filtroEdad);
        MongoCursor<Profesor> cursor = profesEdad.cursor();
        if (cursor.hasNext()) {
            while (cursor.hasNext()) {
                profesor = cursor.next();
                System.out.println(profesor.toString());
        }
        } else {
            System.out.println("No hay ningún docente en ese rango de edad.");
        }
    }

    public boolean checkExistingProf(String campo, String value) {
        boolean encontrado = false;
        Bson filtroBusqueda = Filters.eq(campo,value);
        FindIterable<Profesor> profe = collection.find(filtroBusqueda);
        MongoCursor<Profesor> cursor = profe.cursor();
        if (cursor.hasNext()) {
            profesor = cursor.next();
            return encontrado = true;
        } else {
            System.out.println("No se ha encontrado ningún docente con ese atributo.");
            return encontrado = false;
        }
    }

    public void updateProf(String mail, double newRating) {
        Bson filtradoMail = Filters.eq(DBScheme.keyMail, mail);
        Bson newCalification = Updates.set(DBScheme.keyRating, newRating);
        collection.updateMany(filtradoMail, newCalification);
        System.out.println("Se ha actualizado correctamente la calificación del profesor con correo " + mail
                + " con la nota " + newRating + ".");
    }

    public void showProf() {
        // Cuenta el número total de profesores en la colección
        long totalProfs = collection.countDocuments();
        System.out.println("Se han encontrado " + totalProfs + " docentes.");

        FindIterable<Profesor> allProfs = collection.find();
        MongoCursor<Profesor> cursor = allProfs.cursor();
        while (cursor.hasNext()){
            profesor = cursor.next();
            System.out.println(profesor.toString());
        }

    }








}
