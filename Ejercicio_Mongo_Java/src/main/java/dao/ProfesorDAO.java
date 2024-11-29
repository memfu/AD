package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
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

    }

    public void searchProfByAge(int minAge, int maxAge) {
        Bson filtroEdad = Filters.and(Filters.gte(DBScheme.keyAge, minAge), Filters.lte(DBScheme.keyAge,maxAge));
        FindIterable<Profesor> profesEdad = collection.find(filtroEdad);
        MongoCursor<Profesor> cursor = profesEdad.cursor();
        while (cursor.hasNext()) {
            profesor = cursor.next();
            System.out.println(profesor.toString());
        }
    }

    public void updateProf(String campo) {

    }

    public void showProf() {
        FindIterable<Profesor> allProfs = collection.find();
        MongoCursor<Profesor> cursor = allProfs.cursor();
        while (cursor.hasNext()){
            profesor = cursor.next();
            System.out.println(profesor.toString());
        }

    }








}
