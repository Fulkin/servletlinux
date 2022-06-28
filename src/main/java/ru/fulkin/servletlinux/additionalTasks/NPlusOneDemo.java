package ru.fulkin.servletlinux.additionalTasks;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.fulkin.servletlinux.model.client.Client;
import ru.fulkin.servletlinux.util.HibernateUtil;

import java.util.List;

public class NPlusOneDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;
        List<Client> resultList = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<Client> nPlusOneQuery = session.createQuery("SELECT c FROM client c", Client.class);
            Query<Client> query = session.createQuery("SELECT c FROM client c join fetch c.deals d join fetch d.product p", Client.class);
            resultList = query.getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        resultList.forEach(System.out::println);
    }
}
