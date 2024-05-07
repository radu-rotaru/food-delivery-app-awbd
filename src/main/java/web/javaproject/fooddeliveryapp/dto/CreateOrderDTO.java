package web.javaproject.fooddeliveryapp.dto;

import lombok.Data;

import java.util.List;

@Data
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
}
