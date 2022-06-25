package ru.fulkin.servletlinux.repository;

import ru.fulkin.servletlinux.model.Client;
import ru.fulkin.servletlinux.model.Deal;
import ru.fulkin.servletlinux.model.DealToList;
import ru.fulkin.servletlinux.model.Product;

import java.util.Collection;

public interface ClientRepository {
    Client save(Client client);

    boolean delete(int id);

    Client getClient(int id);

    Collection<Client> getAll();

    Collection<DealToList> getDealList(Client client);

    Collection<Product> getProducts();

    Product getProduct(int id);

    boolean saveDeal(Deal deal);
}
