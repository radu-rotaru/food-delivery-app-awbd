package web.javaproject.fooddeliveryapp.dto;

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

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }

    public CourierDTO getCourierDTO() {
        return courierDTO;
    }

    public void setCourierDTO(CourierDTO courierDTO) {
        this.courierDTO = courierDTO;
    }

    public OrderIdDTO getOrderIdDTO() {
        return orderIdDTO;
    }

    public void setOrderIdDTO(OrderIdDTO orderIdDTO) {
        this.orderIdDTO = orderIdDTO;
    }
}
