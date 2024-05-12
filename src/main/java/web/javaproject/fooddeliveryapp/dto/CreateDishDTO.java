package web.javaproject.fooddeliveryapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class CreateDishDTO  {
    @NotBlank
    private String name;
    @Positive(message = "Quantity must be positive")
    private int quantity;
    @Positive(message = "Price must be positive")
    private float price;

    private Long restaurantId;

    public CreateDishDTO() {
    }

    public CreateDishDTO(String name, int quantity, float price, Long restaurantId) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.restaurantId = restaurantId;
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

    public Long getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
