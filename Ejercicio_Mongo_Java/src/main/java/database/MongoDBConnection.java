package database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Alumno;
import model.Profesor;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class MongoDBConnection {
    private String connectionString = "mongodb+srv://%s:%s@cluster0.4etyp.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
    private MongoClient mongoClient;
    private CodecProvider codecProvider;
    private CodecRegistry codecRegistry;

    public MongoDBConnection() {
        /*
        For finding objects of type user, we get Documents. This document is then consulted by the MongoCursor.
        This takes a lot of time and hassle. Actually we need a List<Usuarios>
        For translating from Document to Object and viceversa we use a CodecProvider called Pojo. The same object, but deconstructed
         */
        codecProvider = PojoCodecProvider.builder().automatic(true).build();
        codecRegistry = CodecRegistries.fromRegistries(
                        MongoClients.create().getCodecRegistry(),
                        CodecRegistries.fromProviders(codecProvider)
                        );

        mongoClient = MongoClients.create(String.format(connectionString, DBScheme.user, DBScheme.pw));
    }

    public MongoCollection getCollection(String nombreColl) {

        MongoDatabase database = mongoClient.getDatabase(DBScheme.dbCentroEstudios).withCodecRegistry(codecRegistry);
        MongoCollection mongoCollection = null;

        if (nombreColl.equalsIgnoreCase("alumnos")) {
            mongoCollection = database.getCollection(DBScheme.collAlumnos, Alumno.class); // We need to specify which model is used for the translation, so we spare work with documents
        } else if (nombreColl.equalsIgnoreCase("profesores")){
            mongoCollection = database.getCollection(DBScheme.collProfesores, Profesor.class); // We need to specify which model is used for the translation, so we spare work with documents
        }

        return mongoCollection;
    }

}
