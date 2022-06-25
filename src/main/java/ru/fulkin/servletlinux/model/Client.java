package ru.fulkin.servletlinux.model;

public class Client {
    private Integer id;

    private final String firstname;

    private final String lastname;

    private final String city;

    private final String phone;


    public Client(Integer id, String firstname, String lastname, String city, String phone) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.phone = phone;
    }

    public Client(String firstname, String lastname, String city, String phone) {
        this(null, firstname, lastname, city, phone);
    }

    public Integer getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
