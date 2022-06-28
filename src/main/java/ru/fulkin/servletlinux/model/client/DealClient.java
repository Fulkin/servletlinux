package ru.fulkin.servletlinux.model.client;

import javax.persistence.*;

@Entity(name = "deal_client")
@Table(name = "deal_client")
public class DealClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "deal_id")
    private int idDeal;

    @Column(name = "client_id")
    private int idClient;

    public DealClient() {
    }

    public DealClient(int idDeal, int idClient) {
        this.idDeal = idDeal;
        this.idClient = idClient;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdDeal() {
        return idDeal;
    }

    public void setIdDeal(int idDeal) {
        this.idDeal = idDeal;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }


}
