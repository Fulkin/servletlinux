package ru.fulkin.servletlinux.additionalTasks;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.fulkin.servletlinux.model.client.Client;
import ru.fulkin.servletlinux.util.HibernateUtil;

public class LazyInitializationDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Client client = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery(
                    "FROM client c WHERE c.id =:idClient")
                    .setParameter("idClient", 1);
            client = (Client) query.getResultList().get(0);
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        System.out.println(client.getDeals());
    }
}
