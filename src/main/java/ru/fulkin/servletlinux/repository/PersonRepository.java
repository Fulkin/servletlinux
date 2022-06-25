package ru.fulkin.servletlinux.repository;

import ru.fulkin.servletlinux.model.Client;

import java.util.Collection;

public interface PersonRepository {
    Client save(Client client);

    boolean delete(int id);

    Client get(int id);

    Collection<Client> getAll();
}
