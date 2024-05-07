package web.javaproject.fooddeliveryapp.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CourierReview courierReview;

    @ManyToMany
    @JoinTable(
            name = "order_dish",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<Dish> dishes;

    @Value("#{T(java.util.Arrays).asList('processed', 'delivered', 'cancelled')}")
    private String status;

    public Order() {}

    public Order(Restaurant restaurant, Client client, Courier courier, List<Dish> dishes, String status) {
        this.restaurant = restaurant;
        this.client = client;
        this.courier = courier;
        this.dishes = dishes;
        this.status = status;
    }
}
