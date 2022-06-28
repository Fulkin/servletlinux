package ru.fulkin.servletlinux.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import ru.fulkin.servletlinux.model.client.Client;
import ru.fulkin.servletlinux.model.client.Deal;
import ru.fulkin.servletlinux.model.client.Product;

import javax.persistence.EntityGraph;
import java.sql.Connection;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class ClientRepositoryImpl implements ClientRepository {
    private static final Logger log = getLogger(ClientRepositoryImpl.class);

    Connection conn;
    private SessionFactory sessionFactory;

    public ClientRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer save(Client client) {
        Transaction transaction = null;
        Integer clientId = 1;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(client);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return clientId;
    }

    @Override
    public boolean delete(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            Client client = session.get(Client.class, id);
            client.setDeals(null);
            session.delete(client);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Client getClient(int id) {
        Transaction transaction = null;
        Client client = null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            EntityGraph entityGraph = session.getEntityGraph("client-entity-graph");

            Query query = session.createQuery(
                    "FROM client c WHERE c.id =:idClient")
                    .setParameter("idClient", id)
                    .setHint("javax.persistence.fetchgraph", entityGraph);
            client = (Client) query.getResultList().get(0);

            //client = session.get(Client.class, id);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public List<Client> getAllClients() {
        Transaction transaction = null;
        List clients = null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            clients = session.createQuery("FROM client ORDER BY id").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public List<Deal> getDealList(Client client) {
        log.info("In getDeadList method");
        List<Deal> dealClients = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            EntityGraph entityGraph = session.getEntityGraph("client-entity-graph");

            Query query = session.createQuery(
                    "FROM client c WHERE c.id =:idClient")
                    .setParameter("idClient", client.getId())
                    .setHint("javax.persistence.fetchgraph", entityGraph);
            Client client1 = (Client) query.getResultList().get(0);
            dealClients = client1.getDeals();

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        log.info("Client have {} deals", dealClients == null ? -1 : dealClients.size());
        return dealClients;
    }

    @Override
    public List<Product> getAllProducts() {
        Transaction transaction = null;
        List products = null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            products = session.createQuery("FROM product ORDER BY id").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getProduct(int id) {
        Transaction transaction = null;
        Product product = null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            product = session.get(Product.class, id);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean saveDeal(Deal deal) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            session.saveOrUpdate(deal.getProduct());
            session.save(deal);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
