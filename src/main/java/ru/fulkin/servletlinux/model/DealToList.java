package ru.fulkin.servletlinux.model;

import java.time.LocalDateTime;

public class DealToList {
    private Integer id;

    private final LocalDateTime date;
    private final String productName;
    private final int amount;
    private final int price;
    private final int sum;

    public DealToList(Integer id, LocalDateTime date, String productName, int amount, int price) {
        this.id = id;
        this.date = date;
        this.productName = productName;
        this.amount = amount;
        this.price = price;
        this.sum = amount * price;
    }

    public DealToList(LocalDateTime date, String productName, int amount, int price) {
        this(null, date, productName, amount, price);
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getProductName() {
        return productName;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public int getSum() {
        return sum;
    }
}
