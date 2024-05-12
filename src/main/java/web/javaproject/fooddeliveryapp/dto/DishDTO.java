package web.javaproject.fooddeliveryapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import web.javaproject.fooddeliveryapp.model.Restaurant;

public class DishDTO {
    private Long id;
    @NotBlank
    private String name;
    @Positive(message = "Quantity must be positive")
    private int quantity;
    @Positive(message = "Price must be positive")
    private float price;

    private RestaurantDTO restaurant;

    public DishDTO() {
    }

    public DishDTO(Long id, String name, int quantity, float price, RestaurantDTO restaurant) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.restaurant = restaurant;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurantDTO) {
        this.restaurant = restaurant;
    }
}
