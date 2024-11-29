package model;

import database.DBScheme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Usuario implements Serializable {
    @BsonProperty(DBScheme.keyName)
    private String nombre;
    @BsonProperty(DBScheme.keyAge)
    private int edad;
    private float rating;
    @BsonProperty(DBScheme.keyGender)
    private String genero;

    @BsonProperty(DBScheme.keyMail)
    private String correo;
    @BsonProperty(DBScheme.keyPhone)
    private String telefono;




    @Override
    public String toString() {
        return "- Nombre: " + getNombre() +
                "\n\t Edad: " + getEdad() +
                "\n\t Género: " + getGenero() +
                "\n\t Rating: " + getRating() +
                "\n\t Correo electrónico: " + getCorreo() +
                "\n\t Teléfono: " + getTelefono()
                ;
    }
}
