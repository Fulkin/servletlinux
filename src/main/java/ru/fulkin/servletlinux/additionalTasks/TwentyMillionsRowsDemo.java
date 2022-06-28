package ru.fulkin.servletlinux.additionalTasks;

import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import ru.fulkin.servletlinux.model.client.Client;
import ru.fulkin.servletlinux.model.client.Product;
import ru.fulkin.servletlinux.util.HibernateUtil;


import javax.persistence.FlushModeType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TwentyMillionsRowsDemo {

    private static Random random = new Random();
    private static int leftLimit = 48; // numeral '0'
    private static int rightLimit = 122; // letter 'z'
    private static int targetStringLength = 10;


    public static void main(String[] args) {

        //addTwentyMillionsRows();
        long m = System.currentTimeMillis();
        getRowsSimpleHql();
        System.out.println((double) (System.currentTimeMillis() - m));

    }

    private static void getRowsSimpleHql() {
        List<Client> resultList = new ArrayList();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            int batchSize = 50;
            session.setJdbcBatchSize(batchSize);
            Query query = session.createQuery("FROM product p WHERE p.remnant > 2000")
                    .setFlushMode(FlushModeType.AUTO).setFetchSize(batchSize);
            resultList = query.getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    private static void addTwentyMillionsRows() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Client client = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            int count = 20_000_000;
            for (int i = 0; i < count; i++) {
                Product product = new Product(
                        generateRandomString(),
                        generateRandomInt(),
                        generateRandomInt()
                );
                session.save(product);
                if(i % 50 == 0){
                    session.flush();
                    session.clear();
                }
                System.out.println(i);
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static String generateRandomString() {
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static int generateRandomInt() {
        return random.nextInt(10_000);
    }
}
