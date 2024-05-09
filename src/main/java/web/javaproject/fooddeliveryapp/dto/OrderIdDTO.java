package web.javaproject.fooddeliveryapp.dto;

public class OrderIdDTO {
    private Long id;

    public OrderIdDTO() {
    }

    public OrderIdDTO(Long orderId) {
        this.id = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
