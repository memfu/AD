package database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public SessionFactory getSessionFactory(){

        if (sessionFactory == null){
            createSessionFactory();
        }

        return sessionFactory;
    }

    private void createSessionFactory() {

        /* File file = new File("src/main/resources/hibernate.cfg.xml"); // En caso de que se llamara de otra forma
         sessionFactory = new Configuration()
                .configure(file).buildSessionFactory();*/

        sessionFactory = new Configuration().configure().buildSessionFactory();
    }
}
