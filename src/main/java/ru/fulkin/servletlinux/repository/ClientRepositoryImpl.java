package ru.fulkin.servletlinux.repository;

import com.zaxxer.hikari.HikariDataSource;
import ru.fulkin.servletlinux.model.Client;
import ru.fulkin.servletlinux.model.Deal;
import ru.fulkin.servletlinux.model.DealToList;
import ru.fulkin.servletlinux.model.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class ClientRepositoryImpl implements ClientRepository {


    private Connection conn = null;
    String url;
    String user;
    String password;

    public ClientRepositoryImpl(HikariDataSource ds) {
        try {
            Class.forName("org.postgresql.Driver");
            conn = ds.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Client save(Client client) {
        String sql;
        Integer id = client.getId();
        if (id == null) {
            sql = "INSERT INTO client (first_name, last_name, city, phone) VALUES (?, ?, ?, ?)";
        } else {
            sql = "UPDATE client" +
                    " SET first_name = ?, last_name = ?, city = ?, phone = ?" +
                    " WHERE id = ?";
        }
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, client.getFirstname());
            preparedStatement.setString(2, client.getLastname());
            preparedStatement.setString(3, client.getCity());
            preparedStatement.setString(4, client.getPhone());
            if (id != null) {
                preparedStatement.setInt(5, id);
            }
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    client.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return client;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM client WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public Client getClient(int id) {
        Client client = null;
        String SQL = "SELECT * FROM client WHERE id = ? ORDER BY id";
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idPerson = resultSet.getInt("id");
                String firstname = resultSet.getString("first_name");
                String lastname = resultSet.getString("last_name");
                String city = resultSet.getString("city");
                String phone = resultSet.getString("phone");
                client = new Client(idPerson, firstname, lastname, city, phone);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return client;
    }

    @Override
    public Collection<Client> getAll() {
        Collection<Client> clients = new ArrayList<>();
        String SQL = "SELECT * FROM client ORDER BY id";
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstname = resultSet.getString("first_name");
                String lastname = resultSet.getString("last_name");
                String city = resultSet.getString("city");
                String phone = resultSet.getString("phone");
                clients.add(new Client(id, firstname, lastname, city, phone));
            }
        } catch (SQLException e) {

        }
        return clients;
    }

    @Override
    public Collection<DealToList> getDealList(Client client) {
        Collection<DealToList> dealToLists = new ArrayList<>();
        String sql = "SELECT deal.id as deal_id, date, product.name as product_name, product.price as price, amount" +
                " FROM deal" +
                " INNER JOIN client ON client.id = deal.client_id" +
                " INNER JOIN product ON product.id = deal.product_id" +
                " WHERE client.id = ?" +
                " ORDER BY date, deal_id";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, client.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("deal_id");
                LocalDateTime date = resultSet.getObject("date", LocalDateTime.class);
                String productName = resultSet.getString("product_name");
                int price = resultSet.getInt("price");
                int amount = resultSet.getInt("amount");
                dealToLists.add(new DealToList(id, date, productName, price, amount));
            }
        } catch (SQLException e) {

        }
        return dealToLists;
    }

    @Override
    public Collection<Product> getProducts() {
        Collection<Product> products = new ArrayList<>();
        String SQL = "SELECT * FROM product ORDER BY id";
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                int remnant = resultSet.getInt("remnant");
                products.add(new Product(id, name, price, remnant));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getProduct(int id) {

        Product product = null;
        String SQL = "SELECT * FROM product WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idProduct = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                int remnant = resultSet.getInt("remnant");
                product = new Product(idProduct, name, price, remnant);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;

    }

    @Override
    public boolean saveDeal(Deal deal) {
        String updateDealSql = "INSERT INTO deal (amount, date, product_id, client_id) VALUES (?, ?, ?, ?)";
        String addProductSql = "UPDATE product" +
                " SET remnant = ?" +
                " WHERE id = ?";
        int transactionlvl = -1;
        try {
            transactionlvl= conn.getTransactionIsolation();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement updateDeal = conn.prepareStatement(updateDealSql);
             PreparedStatement updateProduct = conn.prepareStatement(addProductSql)
        ) {
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            updateDeal.setInt(1, deal.getAmount());
            updateDeal.setObject(2, Timestamp.valueOf(deal.getDate()));

            updateDeal.setInt(3, deal.getIdProduct());
            updateDeal.setInt(4, deal.getIdClient());
            updateDeal.executeUpdate();

            updateProduct.setInt(1, deal.getRemnant());
            updateProduct.setInt(2, deal.getIdProduct());
            updateProduct.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            try {
                conn.setAutoCommit(true);
                if (transactionlvl != -1) {
                    conn.setTransactionIsolation(transactionlvl);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
