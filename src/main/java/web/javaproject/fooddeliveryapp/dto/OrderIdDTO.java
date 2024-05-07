package web.javaproject.fooddeliveryapp.dto;

import lombok.Data;

@Data
public class OrderIdDTO {
    private Long id;

    public OrderIdDTO() {
    }

    public OrderIdDTO(Long orderId) {
        this.id = orderId;
    }
}
