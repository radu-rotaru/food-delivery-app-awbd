package web.javaproject.fooddeliveryapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private RestaurantDTO restaurant;
    private ClientDTO client;
    private CourierDTO courier;
    private List<DishDTO> dishes;
    private String status;

    public OrderDTO() {
    }

    public OrderDTO(RestaurantDTO restaurant, ClientDTO client, CourierDTO courier, List<DishDTO> dishes, String status) {
        this.restaurant = restaurant;
        this.client = client;
        this.courier = courier;
        this.dishes = dishes;
        this.status = status;
    }
}