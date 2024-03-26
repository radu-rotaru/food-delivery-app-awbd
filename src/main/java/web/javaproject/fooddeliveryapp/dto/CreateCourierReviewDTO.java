package web.javaproject.fooddeliveryapp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class CreateCourierReviewDTO {
    private Long clientId;
    private Long courierId;
    private Long orderId;

    @Min(value = 1, message = "Stars should be at least 1")
    @Max(value = 5, message = "Stars cannot be more than 5")
    private int stars;

    private CreateCourierReviewDTO() {
    }

    public CreateCourierReviewDTO(Long clientId, Long courierId, Long orderId, int stars) {
        this.clientId = clientId;
        this.courierId = courierId;
        this.orderId = orderId;
        this.stars = stars;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
