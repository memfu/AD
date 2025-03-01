package dao;

import database.HibernateUtil;
import model.Editorial;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class EditorialDAO {

    private Session session;
    private List<Editorial> listaEditoriales = new ArrayList<Editorial>();

    public List<Editorial> getListaEditoriales() {
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Editorial> query = session.createNamedQuery("Editorial.findAll",Editorial.class);
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
}
