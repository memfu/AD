package dao;

import Validaciones.Validador;
import database.HibernateUtil;
import model.Editorial;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class EditorialDAO {

    private Session session;
    private Validador validador = new Validador();
    private List<Editorial> listaEditoriales = new ArrayList<Editorial>();

    public List<Editorial> getListaEditoriales() {
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Editorial> query = session.createQuery("FROM Editorial",Editorial.class);
        listaEditoriales = query.list();
        session.getTransaction().commit();
        session.close();
        return listaEditoriales;
    }

    public void crearEditorial(Editorial editorial){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(editorial);
        session.getTransaction().commit();
        session.close();
    }

    public Editorial buscarPorNombre(String nombre) {
        nombre = validador.quitarTildes(nombre.toLowerCase());
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Editorial> editorialQuery = session.createQuery
                ("FROM Editorial e WHERE LOWER(e.nombre) = LOWER(:nombre) ", Editorial.class);
        // Parámetro nominal - sino parámetro posicional
        editorialQuery.setParameter("nombre", nombre);
        Editorial editorialEncontrada = editorialQuery.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return editorialEncontrada;
       /* // Alternativa para ponerlo todo de una
       return session.createQuery("FROM Editorial e WHERE LOWER(e.nombre) = LOWER(:nombre) ", Editorial.class)
                .setParameter("nombre", nombre)
                .uniqueResult();*/
    }
}
