package ru.fulkin.servletlinux.service;

import ru.fulkin.servletlinux.model.Client;
import ru.fulkin.servletlinux.repository.PersonRepository;
import ru.fulkin.servletlinux.repository.PersonRepositoryImpl;

import java.io.*;
import java.util.Collection;
import java.util.Properties;

public class ClientService {
    private PersonRepository repository;

    public ClientService() {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("properties\\database.properties")){
            Properties props = new Properties();
            props.load(is);
            repository = new PersonRepositoryImpl(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file");
        }

    }

    public Collection<Client> getAllPerson()  {
        return repository.getAll();
    }

    public Client get(int id) {
        return repository.get(id);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public void save(Client client) {
        repository.save(client);
    }
}
