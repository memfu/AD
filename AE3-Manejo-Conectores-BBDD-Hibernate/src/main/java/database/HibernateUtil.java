package database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    //get Session
    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            createSessionFactory();
        }
        return sessionFactory;
    }

    //create Session
    private void createSessionFactory() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    //close Session
    public void closeSessionFactory() {
        sessionFactory.close();
        sessionFactory = null;
    }
}
