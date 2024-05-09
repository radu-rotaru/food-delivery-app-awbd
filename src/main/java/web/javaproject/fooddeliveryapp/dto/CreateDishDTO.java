package web.javaproject.fooddeliveryapp.dto;

public class CreateDishDTO extends DishDTO {
    private Long restaurantId;

    public CreateDishDTO() {
    }

    public CreateDishDTO(String name, int quantity, float price, Long restaurantId) {
        super(name, quantity, price);
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
