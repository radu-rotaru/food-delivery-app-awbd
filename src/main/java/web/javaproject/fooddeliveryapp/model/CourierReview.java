package web.javaproject.fooddeliveryapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Entity
@Table(name = "courier_reviews")
public class CourierReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "Stars should be at least 1")
    @Max(value = 5, message = "Stars cannot be more than 5")
    private int stars;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public CourierReview() {}

    public CourierReview(int stars, Client client, Courier courier, Order order) {
        this.stars = stars;
        this.client = client;
        this.courier = courier;
        this.order = order;
    }
}