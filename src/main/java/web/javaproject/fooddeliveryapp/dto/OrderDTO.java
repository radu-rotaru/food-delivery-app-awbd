package web.javaproject.fooddeliveryapp.dto;

import java.util.List;

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

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public CourierDTO getCourier() {
        return courier;
    }

    public void setCourier(CourierDTO courier) {
        this.courier = courier;
    }

    public List<DishDTO> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishDTO> dishes) {
        this.dishes = dishes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}