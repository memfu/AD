package dao;

import database.HibernateUtil;
import model.Liga;
import org.hibernate.Session;

public class LigaDao {

    // Session session;

    // persist -> guarda
    // merge -> actualiza - inserta (id)
    // list -> select (id)
    // delete -> (id)

    // nameQuery (si es muy recurrente)  // Query -> HQL (obligatorio para queries complexas no muy recurrentes: no solo con id)
    // nativeQuery // Query -> SQL (último recurso)

    public void crearLiga (Liga liga) {
        /*
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
            Query
        session.getTransaction().commit();
        session.close();
         */
        HibernateUtil.executeTransaction(session ->
            {
            session.persist(liga);
            return null; // Transacciones sin valor devuelto retornan null.
            }
        );
    }

}
