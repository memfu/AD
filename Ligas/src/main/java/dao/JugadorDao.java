package dao;

import database.HibernateUtil;
import model.Jugador;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class JugadorDao {

    public void insertarJugador (Jugador jugador) {
        HibernateUtil.executeTransaction(session ->
            {
            session.persist(jugador);
                System.out.println("Se ha insertado correctamente al jugador.");
            return null; // Transacciones sin valor devuelto retornan null.
            }
        );
    }

    public void actualizarJugador (int id) {
        HibernateUtil.executeTransaction(session -> {
            Jugador jugador = obtenerJugador(id);
            if (jugador != null){
                System.out.println("Qué campo quiere cambiar?");
                session.remove(jugador);
                System.out.println("Se ha eliminado correctamente el jugador con id " + id + ".");
            } else {
                System.out.println("No se ha encontrado ningún jugador con id " + id + ".");
            }
            return null;
        });
    }

    public void eliminarJugador (int id) {
        HibernateUtil.executeTransaction(session ->
        {
            Jugador jugador = obtenerJugador(id);
            if (jugador != null){
                session.remove(jugador);
                System.out.println("Se ha eliminado correctamente el jugador con id " + id + ".");
            } else {
                System.out.println("No se ha encontrado ningún jugador con id " + id + ".");
            }
            return null;
        });
    }

    public Jugador obtenerJugador (int id) {
        return HibernateUtil.executeTransaction(session -> session.get(Jugador.class,id));
    }

    public void obtenerTodosJug () {
        HibernateUtil.executeTransaction(session ->
        {
            Query<Jugador> query = session.createQuery("FROM Jugador", Jugador.class);
            List<Jugador> listaJugadores = query.list();
            for (Jugador j : listaJugadores) {
                System.out.println(j);
            }
            return null;
        });
    }




}
