package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public SessionFactory getSessionFactory (){
        if (sessionFactory == null) {
            createSessionFactory();
        }
        return sessionFactory;
    }

    private void createSessionFactory() {
        sessionFactory = new Configuration().buildSessionFactory();
    }

    public static <T> T executeTransaction(HibernateTransaction<T> action) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = sessionFactory.openSession(); // Abre la sesión manualmente
            transaction = session.beginTransaction();

            T result = action.apply(session); // Ejecuta la lógica personalizada

            transaction.commit(); // Confirma la transacción
            return result;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Revierte la transacción si hay errores
            }
            throw new RuntimeException("Error during transaction", e);
        } finally {
            if (session != null) {
                session.close(); // Cierra la sesión en el bloque finally
            }
        }
    }

    @FunctionalInterface
    public interface HibernateTransaction<T> {
        T apply(Session session);
    }

}
