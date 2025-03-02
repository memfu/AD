package dao;

import database.HibernateUtil;
import model.Primogenito;
import org.hibernate.Session;
import org.hibernate.query.Query;
import validaciones.Validador;

import java.util.ArrayList;
import java.util.List;

public class PrimogenitoDAO {
    private Session session;
    private Validador validador;
    private List<Primogenito> listaPrimogenitos= new ArrayList<Primogenito>();


    public void agregarPrimogenito(Primogenito primogenito) {
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(primogenito);
        session.getTransaction().commit();
        session.close();
    }

    public List<Primogenito> getAllPrimogenitos() {
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Primogenito> query = session.createQuery("FROM Primogenito",Primogenito.class);
        listaPrimogenitos = query.list();
        session.getTransaction().commit();
        session.close();
        return listaPrimogenitos;
    }

    public Primogenito buscarPorNombreYApellidos(String nombre, String apellidos) {
        nombre = validador.quitarTildes(nombre.toLowerCase());
        apellidos = validador.quitarTildes(apellidos.toLowerCase());
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Primogenito> primogenitoQuery = session.createQuery
                ("FROM Primogenito p WHERE LOWER(p.nombre) = :nombre " +
                        "AND LOWER(p.apellidos) = :apellidos", Primogenito.class);
        // Parámetro nominal - sino parámetro posicional
        primogenitoQuery.setParameter("nombre", nombre);
        primogenitoQuery.setParameter("apellidos", apellidos);
        Primogenito primogenitoEncontrado = primogenitoQuery.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return primogenitoEncontrado;
    }
}
