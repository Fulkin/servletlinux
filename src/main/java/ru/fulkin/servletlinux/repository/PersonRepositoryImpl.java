package ru.fulkin.servletlinux.repository;

import ru.fulkin.servletlinux.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PersonRepositoryImpl implements PersonRepository {

    private Connection conn = null;
    String url;
    String user;
    String password;

    public PersonRepositoryImpl(String url, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            this.url = url;
            this.user = user;
            this.password = password;
            conn = DriverManager.getConnection(url, user, password);
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
    public Client get(int id) {
        Client client = null;
        String SQL = "SELECT * FROM client WHERE id = ?";
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
        Collection<Client> people = new ArrayList<>();
        String SQL = "SELECT * FROM client";
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstname = resultSet.getString("first_name");
                String lastname = resultSet.getString("last_name");
                String city = resultSet.getString("city");
                String phone = resultSet.getString("phone");
                people.add(new Client(id, firstname, lastname, city, phone));
            }
        } catch (SQLException e) {

        }
        return people;
    }
}
