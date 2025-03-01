package dao;

import database.HibernateUtil;
import model.Autor;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class AutorDAO {
    private Session session;

    private List<Autor> listaAutores = new ArrayList<Autor>();

    public List<Autor> getListaAutores() {
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Autor> query = session.createNamedQuery("Autor.findAll",Autor.class);
        listaAutores = query.list();
        session.getTransaction().commit();
        session.close();
        return listaAutores;
    }

    public void agregarAutor(Autor autor) {
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(autor);
        session.getTransaction().commit();
        session.close();
    }


}
