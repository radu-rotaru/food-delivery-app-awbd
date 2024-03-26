package web.javaproject.fooddeliveryapp.dto;

import java.util.List;

public class OrderDTO {
    private RestaurantDTO restaurantDTO;

    private ClientDTO clientDTO;

    private CourierDTO courierDTO;

    private List<DishDTO> dishDTO;

    public OrderDTO() {
    }

    public OrderDTO(RestaurantDTO restaurantDTO, ClientDTO clientDTO, CourierDTO courierDTO, List<DishDTO> dishDTO) {
        this.restaurantDTO = restaurantDTO;
        this.clientDTO = clientDTO;
        this.courierDTO = courierDTO;
        this.dishDTO = dishDTO;
    }

    public RestaurantDTO getRestaurantDTO() {
        return restaurantDTO;
    }

    public void setRestaurantDTO(RestaurantDTO restaurantDTO) {
        this.restaurantDTO = restaurantDTO;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }

    public CourierDTO getCourierDTO() {
        return courierDTO;
    }

    public void setCourierDTO(CourierDTO courierDTO) {
        this.courierDTO = courierDTO;
    }

    public List<DishDTO> getDishDTO() {
        return dishDTO;
    }

    public void setDishDTO(List<DishDTO> dishDTO) {
        this.dishDTO = dishDTO;
    }
}