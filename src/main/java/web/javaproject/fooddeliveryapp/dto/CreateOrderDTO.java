package web.javaproject.fooddeliveryapp.dto;

import java.util.List;

public class CreateOrderDTO {
    private Long clientId;
    private Long restaurantId;

    private List<Long> dishesIds;

    public CreateOrderDTO() {
    }

    public CreateOrderDTO(Long clientId, Long restaurantId, List<Long> dishesIds) {
        this.clientId = clientId;
        this.restaurantId = restaurantId;
        this.dishesIds = dishesIds;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<Long> getDishesIds() {
        return dishesIds;
    }

    public void setDishesIds(List<Long> dishesIds) {
        this.dishesIds = dishesIds;
    }
}
