package web.javaproject.fooddeliveryapp.service;

import web.javaproject.fooddeliveryapp.dto.CourierDTO;
import web.javaproject.fooddeliveryapp.model.Courier;

import java.util.List;
import java.util.Optional;

public interface CourierService {
    List<CourierDTO> findAll();
    CourierDTO findById(Long id);
    Optional<Courier> getCourier(Long id);
    public Boolean doesExist(Long courierId);
    CourierDTO save(CourierDTO courierDTO);
    void deleteById(Long id);
    Courier findAvailable();
}
