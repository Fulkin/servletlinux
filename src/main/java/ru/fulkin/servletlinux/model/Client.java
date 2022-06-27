package ru.fulkin.servletlinux.model;

import javax.persistence.*;
import java.util.List;

@NamedEntityGraph(
        name = "client-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("firstname"),
                @NamedAttributeNode("lastname"),
                @NamedAttributeNode("city"),
                @NamedAttributeNode("phone"),
                @NamedAttributeNode(value = "deals", subgraph = "deal-subgraph"),
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "deal-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("date"),
                                @NamedAttributeNode("amount"),
                                @NamedAttributeNode("product")
                        }
                )
        }
)
@Entity(name = "client")
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "city")
    private String city;

    @Column(name = "phone")
    private String phone;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "deal_client",
            joinColumns = {@JoinColumn(name = "client_id")},
            inverseJoinColumns = {@JoinColumn(name = "deal_id")})
    private List<Deal> deals;

    public Client() {
    }

    public Client(String firstname, String lastname, String city, String phone) {
        this(null, firstname, lastname, city, phone);
    }

    public Client(Integer id, String firstname, String lastname, String city, String phone) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }
}
