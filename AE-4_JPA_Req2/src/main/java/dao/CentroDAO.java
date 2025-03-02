package dao;

import database.HibernateUtil;
import model.Centro;
import org.hibernate.Session;
import org.hibernate.query.Query;
import validaciones.Validador;

import java.util.ArrayList;
import java.util.List;

public class CentroDAO {
    private Session session;
    private Validador validador;
    private List<Centro> listaCentros = new ArrayList<Centro>();


    public void agregarCentro(Centro centro) {
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(centro);
        session.getTransaction().commit();
        session.close();
    }

    public List<Centro> getAllCentros() {
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Centro> query = session.createQuery("FROM Centro",Centro.class);
        listaCentros = query.list();
        session.getTransaction().commit();
        session.close();
        return listaCentros;
    }

    public List<Centro> CentrosConMadres(){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Centro> listaCentrosConMadres = session.createQuery("FROM Centro", Centro.class).list();
        session.getTransaction().commit();
        session.close();
        return listaCentrosConMadres;
    }

    public Centro buscarPorNombre(String nombre) {
        nombre = validador.quitarTildes(nombre.toLowerCase());
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Centro> centroQuery = session.createQuery
                ("FROM Centro c WHERE LOWER(c.nombre) = LOWER(:nombre) ", Centro.class);
        // Parámetro nominal - sino parámetro posicional
        centroQuery.setParameter("nombre", nombre);
        Centro centroEncontrado = centroQuery.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return centroEncontrado;
    }
}
