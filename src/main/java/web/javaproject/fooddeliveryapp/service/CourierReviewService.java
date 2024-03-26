package web.javaproject.fooddeliveryapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import web.javaproject.fooddeliveryapp.dto.CreateCourierReviewDTO;
import web.javaproject.fooddeliveryapp.exception.*;
import web.javaproject.fooddeliveryapp.model.Client;
import web.javaproject.fooddeliveryapp.model.Courier;
import web.javaproject.fooddeliveryapp.model.CourierReview;
import web.javaproject.fooddeliveryapp.model.Order;
import web.javaproject.fooddeliveryapp.repository.CourierReviewRepository;

import java.util.Optional;

@Service
@Validated
public class CourierReviewService {
    private final CourierReviewRepository courierReviewRepository;
    private final ClientService clientService;

    private final CourierService courierService;

    private final OrderService orderService;

    @Autowired
    public CourierReviewService(CourierReviewRepository courierReviewRepository, ClientService clientService, CourierService courierService, OrderService orderService) {
        this.clientService = clientService;
        this.courierService = courierService;
        this.orderService = orderService;
        this.courierReviewRepository = courierReviewRepository;
    }

    public CourierReview createCourierReview(CreateCourierReviewDTO createCourierReviewDTO) {
        Optional<Client> client = clientService.getClient(createCourierReviewDTO.getClientId());
        if (client.isEmpty()) {
            throw new ClientDoesNotExistException();
        }

        Optional<Courier> courier = courierService.getCourier(createCourierReviewDTO.getCourierId());
        if (courier.isEmpty()) {
            throw new CourierDoesNotExistException();
        }

        Optional<Order> order = orderService.getOrder(createCourierReviewDTO.getOrderId());
        if (order.isEmpty()) {
            throw new OrderDoesNotExistException();
        }

        Optional<CourierReview> existingCourierReview = courierReviewRepository.findByClientIdAndCourierIdAndOrderId(createCourierReviewDTO.getClientId(), createCourierReviewDTO.getCourierId(), createCourierReviewDTO.getOrderId());

        if (existingCourierReview.isPresent()) {
            throw new CourierReviewAlreadyExistsException();
        }

        if (order.get().getClient() != client.get() || order.get().getCourier() != courier.get()) {
           throw new OrderIsNotValidException();
        }

        CourierReview courierReview = new CourierReview(createCourierReviewDTO.getStars(), client.get(), courier.get(), order.get());

        return courierReviewRepository.save(courierReview);
    }
}
