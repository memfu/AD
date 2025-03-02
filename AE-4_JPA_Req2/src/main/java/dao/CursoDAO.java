package dao;

import database.HibernateUtil;
import model.Centro;
import model.Curso;
import org.hibernate.Session;
import org.hibernate.query.Query;
import validaciones.Validador;

import java.util.ArrayList;
import java.util.List;

public class CursoDAO {
    private Session session;
    private Validador validador;
    private List<Curso> listaCursos = new ArrayList<Curso>();


    public void agregarCurso(Curso curso) {
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(curso);
        session.getTransaction().commit();
        session.close();
    }

    public List<Curso> getAllCursos() {
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Curso> query = session.createQuery("FROM Curso",Curso.class);
        listaCursos = query.list();
        session.getTransaction().commit();
        session.close();
        return listaCursos;
    }

    public List<Curso> CursosConMadres(){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Curso> listaCursosConMadres = session.createQuery("FROM Curso", Curso.class).list();
        session.getTransaction().commit();
        session.close();
        return listaCursosConMadres;
    }

    public Curso buscarPorNombre(String nombre) {
        nombre = validador.quitarTildes(nombre.toLowerCase());
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Curso> cursoQuery = session.createQuery
                ("FROM Curso c WHERE LOWER(c.nombre) = LOWER(:nombre) ", Curso.class);
        // Parámetro nominal - sino parámetro posicional
        cursoQuery.setParameter("nombre", nombre);
        Curso cursoEncontrado = cursoQuery.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return cursoEncontrado;
    }
}
