package ru.fulkin.servletlinux.model.client;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NamedEntityGraph(
        name = "deal-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("date"),
                @NamedAttributeNode("amount"),
                @NamedAttributeNode(value = "product", subgraph = "product-graph"),
                @NamedAttributeNode(value = "clients", subgraph = "client-graph"),
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "product-graph",
                        attributeNodes = {
                                @NamedAttributeNode("name"),
                                @NamedAttributeNode("price"),
                                @NamedAttributeNode("remnant")
                        }
                ),
                @NamedSubgraph(
                        name = "client-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("first_name"),
                                @NamedAttributeNode("last_name"),
                                @NamedAttributeNode("city"),
                                @NamedAttributeNode("phone")
                        }
                )
        }
)
@Entity(name = "deal")
@Table(name = "deal")
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "amount")
    private int amount;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "deal_client",
            joinColumns = {@JoinColumn(name = "deal_id")},
            inverseJoinColumns = {@JoinColumn(name = "client_id")})
    private List<Client> clients;

    public Deal() {
    }

    public Deal(LocalDateTime date, int amount, Product product, List<Client> clients) {
        this.date = date;
        this.amount = amount;
        this.product = product;
        this.clients = clients;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
