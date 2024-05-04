package web.javaproject.fooddeliveryapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import web.javaproject.fooddeliveryapp.dto.CreateCourierReviewDTO;
import web.javaproject.fooddeliveryapp.exception.*;
import web.javaproject.fooddeliveryapp.mapper.CourierReviewMapper;
import web.javaproject.fooddeliveryapp.model.Client;
import web.javaproject.fooddeliveryapp.model.Courier;
import web.javaproject.fooddeliveryapp.model.CourierReview;
import web.javaproject.fooddeliveryapp.model.Order;
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

    public CourierReview createCourierReview(CreateCourierReviewDTO createCourierReviewDTO) {
        Client client = clientService.getClient(createCourierReviewDTO.getClientId());

        Optional<Courier> courier = courierService.getCourier(createCourierReviewDTO.getCourierId());
        if (courier.isEmpty()) {
            throw new CourierDoesNotExistException();
        }

        Order order = orderService.getOrder(createCourierReviewDTO.getOrderId());

        Optional<CourierReview> existingCourierReview = courierReviewRepository.findByClientIdAndCourierIdAndOrderId(createCourierReviewDTO.getClientId(), createCourierReviewDTO.getCourierId(), createCourierReviewDTO.getOrderId());

        if (existingCourierReview.isPresent()) {
            throw new CourierReviewAlreadyExistsException();
        }

        if (order.getClient() != client || order.getCourier() != courier.get()) {
           throw new OrderIsNotValidException();
        }

        CourierReview courierReview = courierReviewMapper.toCourierReviewFromCreated(createCourierReviewDTO);
        return courierReviewRepository.save(courierReview);
    }

    public List<CourierReview> getCourierReviewsByCourierId(Long courierId) {
        Optional<Courier> courier = courierService.getCourier(courierId);
        if (courier.isPresent()) {
            return courierReviewRepository.findByCourierId(courierId);
        } else {
            throw new CourierDoesNotExistException();
        }
    }
}
