package ru.fulkin.servletlinux.model;

import javax.persistence.*;

@Entity(name = "product")
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "remnant")
    private int remnant;

    public Product() {
    }

    public Product(Integer id, String name, int price, int remnant) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.remnant = remnant;
    }

    public Product(String name, int price, int remnant) {
        this(null, name, price, remnant);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRemnant() {
        return remnant;
    }

    public void setRemnant(int remnant) {
        this.remnant = remnant;
    }
}
