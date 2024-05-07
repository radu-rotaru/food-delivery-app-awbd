package web.javaproject.fooddeliveryapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String email;
    private String openingHours;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Dish> dishes;

    public Restaurant() {}

    public Restaurant(String name, String email, String address, String openingHours) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.openingHours = openingHours;
    }

    public Restaurant(String name, String email, String address, List<Dish> dishes) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.dishes = dishes;
    }
}
