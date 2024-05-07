package web.javaproject.fooddeliveryapp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
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
}
