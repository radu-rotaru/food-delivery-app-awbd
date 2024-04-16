package web.javaproject.fooddeliveryapp.service;

import web.javaproject.fooddeliveryapp.dto.RestaurantDTO;

import java.util.List;

public interface RestaurantService {
    List<RestaurantDTO> findAll();
}
