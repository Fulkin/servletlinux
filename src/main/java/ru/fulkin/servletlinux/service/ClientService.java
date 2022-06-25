package ru.fulkin.servletlinux.service;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.fulkin.servletlinux.model.Client;
import ru.fulkin.servletlinux.model.Deal;
import ru.fulkin.servletlinux.model.DealToList;
import ru.fulkin.servletlinux.model.Product;
import ru.fulkin.servletlinux.repository.ClientRepository;
import ru.fulkin.servletlinux.repository.ClientRepositoryImpl;

import java.io.*;
import java.util.Collection;
import java.util.Properties;

public class ClientService {
    private ClientRepository repository;
    private HikariConfig config = new HikariConfig();

    public ClientService() {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("properties\\database.properties")){
            Class.forName("org.postgresql.Driver");
            Properties props = new Properties();
            props.load(is);
            config.setJdbcUrl(props.getProperty("db.url"));
            config.setUsername(props.getProperty("db.user"));
            config.setPassword(props.getProperty("db.password"));

            HikariDataSource ds = new HikariDataSource(config);
            repository = new ClientRepositoryImpl(ds);
        } catch (ClassNotFoundException | IOException e) {
            throw new IllegalStateException("Invalid config file");
        }

    }

    public Collection<Client> getAllPerson()  {
        return repository.getAll();
    }

    public Client get(int id) {
        return repository.getClient(id);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public void save(Client client) {
        repository.save(client);
    }

    public Collection<DealToList> getDealList(Client client) {
        return repository.getDealList(client);
    }

    public Collection<Product> getProducts() {
        return repository.getProducts();
    }

    public Product getProduct(int id){
        return repository.getProduct(id);
    }

    public void saveDeal(Deal deal) {
        repository.saveDeal(deal);
    }

    public Collection<Product> getAllProducts() {
        return repository.getProducts();
    }
}
