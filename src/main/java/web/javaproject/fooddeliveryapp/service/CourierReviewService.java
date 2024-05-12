package web.javaproject.fooddeliveryapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import web.javaproject.fooddeliveryapp.dto.CourierReviewDTO;
import web.javaproject.fooddeliveryapp.exception.*;
import web.javaproject.fooddeliveryapp.mapper.CourierReviewMapper;
import web.javaproject.fooddeliveryapp.model.*;
import web.javaproject.fooddeliveryapp.repository.CourierReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class CourierReviewService {
    private final CourierReviewRepository courierReviewRepository;

    private final CourierReviewMapper courierReviewMapper;

    private final ClientService clientService;

    private final CourierService courierService;

    private final OrderService orderService;

    @Autowired
    public CourierReviewService(CourierReviewRepository courierReviewRepository, CourierReviewMapper courierReviewMapper, ClientService clientService, CourierService courierService, OrderService orderService) {
        this.clientService = clientService;
        this.courierService = courierService;
        this.orderService = orderService;
        this.courierReviewRepository = courierReviewRepository;
        this.courierReviewMapper = courierReviewMapper;
    }

    public List<CourierReview> getCourierReviewsByCourierId(Long courierId) {
        Optional<Courier> courier = courierService.getCourier(courierId);
        if (courier.isPresent()) {
            return courierReviewRepository.findByCourierId(courierId);
        } else {
            throw new CourierDoesNotExistException();
        }
    }

    public CourierReview getCourierReview(Long id) {
        Optional<CourierReview> courierReview = courierReviewRepository.findById(id);

        if (courierReview.isPresent()) {
            return courierReview.get();
        } else {
            throw new CourierDoesNotExistException();
        }
    }

    public CourierReviewDTO save (CourierReviewDTO courierReviewDTO) {
        CourierReview savedCourierReview = courierReviewRepository.save(courierReviewMapper.toCourierReview(courierReviewDTO));
        return courierReviewMapper.toDto(savedCourierReview);
    }

    public void deleteById(Long id){courierReviewRepository.deleteById(id);}
}
