package web.javaproject.fooddeliveryapp.dto;

import java.util.List;

public class CreateOrderDTO {
    private Long clientId;
    private Long courierId;
    private Long restaurantId;

    private List<Long> dishesIds;

    public CreateOrderDTO() {
    }

    public CreateOrderDTO(Long clientId, Long courierId, Long restaurantId, List<Long> dishesIds) {
        this.clientId = clientId;
        this.courierId = courierId;
        this.restaurantId = restaurantId;
        this.dishesIds = dishesIds;
    }

    public Long getClientId() {
        return clientId;
    }

    public Long getCourierId() {
        return courierId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public List<Long> getDishesIds() {
        return dishesIds;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setDishesIds(List<Long> dishesIds) {
        this.dishesIds = dishesIds;
    }
}
