package dao;

import database.HibernateUtil;
import model.Libreria;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class LibreriaDAO {

    private Session session;
    private List<Libreria> listaLibrerias = new ArrayList<Libreria>();


    public void crearLibreria (Libreria libreria){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(libreria);
        session.getTransaction().commit();
        session.close();
    }

    public List<Libreria> getAllLibrerias() {
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Libreria> query = session.createQuery("FROM Libreria",Libreria.class);
        listaLibrerias = query.list();
        session.getTransaction().commit();
        session.close();
        return listaLibrerias;
    }

    public List<Libreria> libreriasConLibros(){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Libreria> listaLibreriasConLibros = session.createQuery("FROM Libreria", Libreria.class).list();
        session.getTransaction().commit();
        session.close();
        return listaLibreriasConLibros;
    }
}
