package web.javaproject.fooddeliveryapp.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "couriers")
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;
    private boolean available;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "courier", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "courier", cascade = CascadeType.ALL)
    private List<CourierReview> courierReviews;


    public Courier() {}

    public Courier(String name, boolean available, String phoneNumber) {
        this.name = name;
        this.available = available;
        this.phoneNumber = phoneNumber;
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


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrder(List<Order> order) {
        this.orders = order;
    }

    public List<CourierReview> getCourierReviews() {
        return courierReviews;
    }

    public void setCourierReviews(List<CourierReview> courierReviews) {
        this.courierReviews = courierReviews;
    }
}