package dao;

import Validaciones.Validador;
import database.HibernateUtil;
import model.Autor;
import model.Libro;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class AutorDAO {
    private Session session;
    private Validador validador;
    private List<Autor> listaAutores = new ArrayList<Autor>();


    public void agregarAutor(Autor autor) {
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(autor);
        session.getTransaction().commit();
        session.close();
    }

    public Autor buscarPorNombreYApellidos(String nombre, String apellidos) {
        nombre = validador.quitarTildes(nombre.toLowerCase());
        apellidos = validador.quitarTildes(apellidos.toLowerCase());
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        return session.createQuery("FROM Autor a WHERE LOWER(a.nombre) = :nombre " +
                        "AND LOWER(apellidos) = :apellidos", Autor.class)
                .setParameter("nombre", nombre)
                .setParameter("apellido", apellidos)
                .uniqueResult();
    }

    public List<Autor> getListaAutores() {
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Autor> query = session.createNamedQuery("Autor.findAll",Autor.class);
        listaAutores = query.list();
        session.getTransaction().commit();
        session.close();
        return listaAutores;
    }


    public List<Libro> getAllLibrosporAutor(int idAutor) {
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Autor autor = session.get(Autor.class, idAutor);
        session.getTransaction().commit();
        session.close();
        return autor.getLibrosEscritos();
    }


}
