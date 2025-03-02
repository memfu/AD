package dao;

import database.HibernateUtil;
import model.Curso;
import model.Madre;
import org.hibernate.Session;
import org.hibernate.query.Query;
import validaciones.Validador;

import java.util.ArrayList;
import java.util.List;

public class MadreDAO {
    private Session session;
    private Validador validador;
    private List<Madre> listaMadres = new ArrayList<Madre>();


    public void agregarMadre(Madre madre) {
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(madre);
        session.getTransaction().commit();
        session.close();
    }

    public List<Madre> getAllMadres() {
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Madre> query = session.createQuery("FROM Madre",Madre.class);
        listaMadres = query.list();
        session.getTransaction().commit();
        session.close();
        return listaMadres;
    }

    public List<Madre> MadresEnCursos(){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Madre> listaCursosApuntados = session.createQuery("FROM Madre", Madre.class).list();
        session.getTransaction().commit();
        session.close();
        return listaCursosApuntados;
    }

    public Madre buscarPorNombreYApellidos(String nombre, String apellidos) {
        nombre = validador.quitarTildes(nombre.toLowerCase());
        apellidos = validador.quitarTildes(apellidos.toLowerCase());
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Madre> madreQuery = session.createQuery
                ("FROM Madre m WHERE LOWER(m.nombre) = :nombre " +
                        "AND LOWER(m.apellidos) = :apellidos", Madre.class);
        // Parámetro nominal - sino parámetro posicional
        madreQuery.setParameter("nombre", nombre);
        madreQuery.setParameter("apellidos", apellidos);
        Madre madreEncontrada = madreQuery.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return madreEncontrada;
    }
}
