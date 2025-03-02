package dao;

import database.HibernateUtil;
import model.Autor;
import model.Editorial;
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
        session.persist(libro);
        session.getTransaction().commit();
        session.close();
    }


    /*
        Mostrar todos los libros dados de alta, con su editorial y su autor
        Mostrar todos los libros dados de alta, y en la librería en la que están.
     */

    public List<Libro> getAllLibros() {
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Libro> query = session.createNamedQuery("Libro.findAll",Libro.class);
        listaLibros = query.list();
        session.getTransaction().commit();
        session.close();
        return listaLibros;
    }



}
