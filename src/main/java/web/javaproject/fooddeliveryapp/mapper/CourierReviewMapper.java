package web.javaproject.fooddeliveryapp.mapper;

import org.mapstruct.Mapper;
import web.javaproject.fooddeliveryapp.dto.CourierReviewDTO;
import web.javaproject.fooddeliveryapp.model.CourierReview;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RestaurantMapper.class, ClientMapper.class, CourierMapper.class, DishMapper.class})
public interface CourierReviewMapper {
    CourierReviewDTO toDto (CourierReview courierReview);
    CourierReview toCourierReview(CourierReviewDTO courierReviewDTO);
    List<CourierReviewDTO> toDTOsList(List<CourierReview> courierReviews);
}
