package ru.fulkin.servletlinux.service;

import com.zaxxer.hikari.HikariConfig;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import ru.fulkin.servletlinux.model.Client;
import ru.fulkin.servletlinux.model.Deal;
import ru.fulkin.servletlinux.model.Product;
import ru.fulkin.servletlinux.repository.ClientRepository;
import ru.fulkin.servletlinux.repository.ClientRepositoryImpl;

import java.util.Collection;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class ClientService {
    private static final Logger log = getLogger(ClientService.class);
    private SessionFactory sessionFactory;
    private ClientRepository repository;
    private HikariConfig config = new HikariConfig();

    public ClientService() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
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
