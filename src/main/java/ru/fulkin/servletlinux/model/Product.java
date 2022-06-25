package ru.fulkin.servletlinux.model;

public class Product {
    private Integer id;

    private final String name;

    private final int price;

    private final int remnant;

    public Product(Integer id, String name, int price, int remnant) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.remnant = remnant;
    }

    public Product(String name, int price, int remnant) {
        this(null, name, price, remnant);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getRemnant() {
        return remnant;
    }
}
