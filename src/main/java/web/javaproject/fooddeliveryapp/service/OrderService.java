package web.javaproject.fooddeliveryapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import web.javaproject.fooddeliveryapp.dto.CreateOrderDTO;
import web.javaproject.fooddeliveryapp.exception.*;
import web.javaproject.fooddeliveryapp.model.*;
import web.javaproject.fooddeliveryapp.repository.CourierRepository;
import web.javaproject.fooddeliveryapp.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class OrderService {
    private final OrderRepository orderRepository;

    private final CourierRepository courierRepository;

    private final ClientService clientService;

    private final CourierService courierService;

    private final RestaurantService restaurantService;

    private final DishService dishService;

    @Autowired
    public OrderService(OrderRepository orderRepository, CourierRepository courierRepository, ClientService clientService, CourierService courierService, RestaurantService restaurantService, DishService dishService) {
        this.orderRepository = orderRepository;
        this.courierRepository = courierRepository;
        this.clientService = clientService;
        this.courierService = courierService;
        this.restaurantService = restaurantService;
        this.dishService = dishService;
    }
}

//    public Order createOrder(CreateOrderDTO createOrderDTO) {
//        Optional<Client> client = clientService.getClient(createOrderDTO.getClientId());
//        if (client.isEmpty()) {
//            throw new ClientDoesNotExistException();
//        }
//
////        Optional<Restaurant> restaurant = restaurantService.getRestaurant(createOrderDTO.getRestaurantId());
////        if (restaurant.isEmpty()) {
////            throw new RestaurantDoesNotExistException();
////        }
//
//        Optional<Courier> courier = courierService.getCourier(createOrderDTO.getCourierId());
//        if (courier.isEmpty()) {
//            throw new CourierDoesNotExistException();
//        }
//
//        if (!courier.get().isAvailable()) {
//            throw new CourierNotAvailableException();
//        }
//
//        List<Dish> dishes = new ArrayList<>();
//        for (Long dishesId : createOrderDTO.getDishesIds()) {
//            Optional<Dish> dish = dishService.getDish(dishesId);
//
//            if (dish.isEmpty()) {
//                throw new DishDoesNotExistException();
//            }
//
//            if (dish.get().getRestaurant().getId() != createOrderDTO.getRestaurantId()) {
//                throw new DishNotOnMenuException();
//            }
//
//            dishes.add(dish.get());
//        }
//
//        Order order = new Order(restaurant.get(), client.get(), courier.get(), dishes);
//
//        order.getCourier().setAvailable(false);
//        courierRepository.save(order.getCourier());
//
//        return orderRepository.save(order);
//    }
//
//    public Optional<Order> getOrder(Long orderId) {
//        return orderRepository.findById(orderId);
//    }
//}