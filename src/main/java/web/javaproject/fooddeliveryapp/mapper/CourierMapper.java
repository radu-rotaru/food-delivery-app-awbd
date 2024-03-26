package web.javaproject.fooddeliveryapp.mapper;

import org.springframework.stereotype.Component;
import web.javaproject.fooddeliveryapp.dto.CourierDTO;
import web.javaproject.fooddeliveryapp.model.Courier;

@Component
public class CourierMapper {
    public CourierDTO toDTO(Courier courier) {
        return new CourierDTO(
            courier.getName(),
            courier.getPhoneNumber()
        );
    }
}
