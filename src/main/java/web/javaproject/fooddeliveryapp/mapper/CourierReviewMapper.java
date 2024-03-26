package web.javaproject.fooddeliveryapp.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.javaproject.fooddeliveryapp.dto.CourierReviewDTO;
import web.javaproject.fooddeliveryapp.model.CourierReview;

@Component
public class CourierReviewMapper {
    private final ClientMapper clientMapper;
    private final CourierMapper courierMapper;
    private final OrderMapper orderMapper;

    @Autowired
    public CourierReviewMapper(ClientMapper clientMapper, CourierMapper courierMapper, OrderMapper orderMapper) {
        this.clientMapper = clientMapper;
        this.courierMapper = courierMapper;
        this.orderMapper = orderMapper;
    }
    public CourierReviewDTO toDTO(CourierReview courierReview) {
        return new CourierReviewDTO(
            clientMapper.toDTO(courierReview.getClient()),
            courierMapper.toDTO(courierReview.getCourier()),
            orderMapper.toIdDTO(courierReview.getOrder())
        );
    }
}
