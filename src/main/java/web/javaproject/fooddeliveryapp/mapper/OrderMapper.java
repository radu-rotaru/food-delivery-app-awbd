package web.javaproject.fooddeliveryapp.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.javaproject.fooddeliveryapp.dto.*;
import web.javaproject.fooddeliveryapp.model.Client;
import web.javaproject.fooddeliveryapp.model.Dish;
import web.javaproject.fooddeliveryapp.model.Order;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    private final RestaurantMapper restaurantMapper;
    private final ClientMapper clientMapper;
    private final CourierMapper courierMapper;
    private final DishMapper dishMapper;

    @Autowired
    public OrderMapper(RestaurantMapper restaurantMapper, ClientMapper clientMapper, CourierMapper courierMapper, DishMapper dishMapper) {
        this.restaurantMapper = restaurantMapper;
        this.clientMapper = clientMapper;
        this.courierMapper = courierMapper;
        this.dishMapper = dishMapper;
    }

    public OrderDTO toDTO(Order order) {
        return new OrderDTO(
            restaurantMapper.toDTO(order.getRestaurant()),
            clientMapper.toDTO(order.getClient()),
            courierMapper.toDTO(order.getCourier()),
            dishMapper.toDTOsList(order.getDishes())
        );
    }

    public OrderIdDTO toIdDTO(Order order) {
        return new OrderIdDTO(
            order.getId()
        );
    }

    public List<OrderDTO> toDTOsList(List<Order> orders) {
        List<OrderDTO> ordersDTOs = new ArrayList<OrderDTO>();

        for (Order order :  orders) {
            ordersDTOs.add(toDTO(order));
        }

        return ordersDTOs;
    }
}
