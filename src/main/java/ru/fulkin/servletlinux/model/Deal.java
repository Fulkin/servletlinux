package ru.fulkin.servletlinux.model;

import java.time.LocalDateTime;

public class Deal {
    private final LocalDateTime date;
    private final int amount;
    private final int idProduct;
    private final int idClient;
    private final int remnant;

    public Deal(LocalDateTime date, int amount, int idProduct, int idClient, int remnant) {
        this.date = date;
        this.amount = amount;
        this.idProduct = idProduct;
        this.idClient = idClient;
        this.remnant = remnant;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getRemnant() {
        return remnant;
    }
}
