package ru.fulkin.servletlinux.repository;

import ru.fulkin.servletlinux.model.client.Client;
import ru.fulkin.servletlinux.model.client.Deal;
import ru.fulkin.servletlinux.model.client.Product;

import java.util.List;

public interface ClientRepository {
    Integer save(Client client);

    boolean delete(int id);

    Client getClient(int id);

    List<Client> getAllClients();

    List<Deal> getDealList(Client client);

    List<Product> getAllProducts();

    Product getProduct(int id);

    boolean saveDeal(Deal deal);
}
