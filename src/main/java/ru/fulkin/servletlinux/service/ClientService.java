package ru.fulkin.servletlinux.service;

import com.zaxxer.hikari.HikariConfig;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import ru.fulkin.servletlinux.model.client.Client;
import ru.fulkin.servletlinux.model.client.Deal;
import ru.fulkin.servletlinux.model.client.Product;
import ru.fulkin.servletlinux.repository.ClientRepository;
import ru.fulkin.servletlinux.repository.ClientRepositoryImpl;
import ru.fulkin.servletlinux.util.HibernateUtil;

import java.util.Collection;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class ClientService {
    private static final Logger log = getLogger(ClientService.class);
    private SessionFactory sessionFactory;
    private ClientRepository repository;
    private HikariConfig config = new HikariConfig();

    public ClientService() {
        sessionFactory = HibernateUtil.getSessionFactory();
        repository = new ClientRepositoryImpl(sessionFactory);
    }

    public List<Client> getAllClients()  {
        return repository.getAllClients();
    }

    public Client get(int id) {
        return repository.getClient(id);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public Integer save(Client client) {
        return repository.save(client);
    }

    public Collection<Deal> getDealList(Client client) {
        log.info("inset in getDealList method");
        return repository.getDealList(client);
    }

    public List<Product> getProducts() {
        return repository.getAllProducts();
    }

    public Product getProduct(int id){
        return repository.getProduct(id);
    }

    public void saveDeal(Deal deal) {
        repository.saveDeal(deal);
    }

    public Collection<Product> getAllProducts() {
        return repository.getAllProducts();
    }
}
