package web.javaproject.fooddeliveryapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import web.javaproject.fooddeliveryapp.model.Courier;
import web.javaproject.fooddeliveryapp.repository.CourierRepository;

import java.util.Optional;

@Service
@Validated
public class CourierService {

    private final CourierRepository courierRepository;

    @Autowired
    public CourierService(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    public Optional<Courier> getCourier(Long courierId) {
        return courierRepository.findById(courierId);
    }
    public Boolean doesExist(Long courierId) {
        return courierRepository.findById(courierId).isPresent();
    }
}