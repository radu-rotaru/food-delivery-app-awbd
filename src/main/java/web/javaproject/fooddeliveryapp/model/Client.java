package web.javaproject.fooddeliveryapp.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "Address cannot be blank")
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private List<Order> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private List<CourierReview> courierReviews;

    public Client() {}

    public Client(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Client(Long id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<CourierReview> getCourierReviews() {
        return courierReviews;
    }

    public void setCourierReviews(List<CourierReview> courierReviews) {
        this.courierReviews = courierReviews;
    }
}