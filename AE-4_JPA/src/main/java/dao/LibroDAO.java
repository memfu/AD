package dao;

import database.HibernateUtil;
import model.Libro;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    private Session session;
    private List<Libro> listaLibros = new ArrayList<Libro>();

    public void crearLibro(Libro libro) {
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // Tengo que hacer primero un merge, porque sino da problemas de detached entity
        libro.setAutor(session.merge(libro.getAutor()));
        libro.setEditorial(session.merge(libro.getEditorial()));

        session.persist(libro); // Persistir solo el nuevo libro
        session.getTransaction().commit();
        session.close();
    }

    public List<Libro> getAllLibros() {
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Libro> query = session.createQuery("FROM Libro",Libro.class);
        listaLibros = query.list();
        session.getTransaction().commit();
        session.close();
        return listaLibros;
    }

    public List<Libro> librosConLibrerias(){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        /*
        Como ya tengo la relación ManyToMany tanto en Libro como en Libreria metidas en el correspondiente
        atributo, no necesito hacer nada más
        */
        List<Libro> listaLibrosConLibrerias = session.createQuery("FROM Libro", Libro.class).list();
        session.getTransaction().commit();
        session.close();
        return listaLibrosConLibrerias;
    }
}
