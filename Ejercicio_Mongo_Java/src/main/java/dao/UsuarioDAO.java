package dao;

import controllers.Validador;
import helpers.PreguntaRespuesta;
import model.Usuario;

import java.util.Scanner;

public class UsuarioDAO {
    Usuario usuario;

    public Usuario addUsuarioGeneral (String nombre, int edad, float rating, String genero, String correo, String telefono) {
        usuario = new Usuario(nombre,edad,rating,genero,correo,telefono);
        return usuario;
    }

}
