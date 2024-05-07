package web.javaproject.fooddeliveryapp.dto;

import lombok.Data;

@Data
public class CourierReviewDTO {
    private ClientDTO clientDTO;
    private CourierDTO courierDTO;
    private OrderIdDTO orderIdDTO;

    public CourierReviewDTO() {
    }

    public CourierReviewDTO(ClientDTO clientDTO, CourierDTO courierDTO, OrderIdDTO orderIdDTO) {
        this.clientDTO = clientDTO;
        this.courierDTO = courierDTO;
        this.orderIdDTO = orderIdDTO;
    }
}
