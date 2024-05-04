package web.javaproject.fooddeliveryapp.mapper;

import org.mapstruct.Mapper;
import web.javaproject.fooddeliveryapp.dto.OrderDTO;
import web.javaproject.fooddeliveryapp.dto.OrderIdDTO;
import web.javaproject.fooddeliveryapp.model.Order;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {RestaurantMapper.class, ClientMapper.class, CourierMapper.class, DishMapper.class})
public interface OrderMapper {
    OrderDTO toDTO (Order order);
    Order toOrder (OrderDTO orderDTO);

    OrderIdDTO toIdDTO(Order order);

    public List<OrderDTO> toDTOsList(List<Order> orders);
}