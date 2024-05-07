package web.javaproject.fooddeliveryapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
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
}
