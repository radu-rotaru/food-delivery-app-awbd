package web.javaproject.fooddeliveryapp.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import web.javaproject.fooddeliveryapp.model.Client;
import web.javaproject.fooddeliveryapp.model.Courier;
import web.javaproject.fooddeliveryapp.model.Order;
import web.javaproject.fooddeliveryapp.model.Restaurant;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CourierRepository courierRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    public void insertOrder() {

        Client client = clientRepository.findById(1L).get();
        Courier courier = courierRepository.findById(1L).get();
        Restaurant restaurant = restaurantRepository.findById(1L).get();

        Order order = new Order();
        order.setStatus("processed");
        order.setClient(client);
        order.setCourier(courier);
        order.setRestaurant(restaurant);


        orderRepository.save(order);

        Optional<Order> orderOpt = orderRepository.findById(1l);
        order = orderOpt.get();
        assertEquals("processed", order.getStatus());
        assertEquals(client, order.getClient());
        assertEquals(courier, order.getCourier());
        assertEquals(restaurant, order.getRestaurant());
    }

    @Test
    public void updateStatus(){
        Optional<Order> orderOpt = orderRepository.findById(1L);
        Order order =orderOpt.get();
        order.setStatus("finished");

        orderRepository.save(order);

        orderOpt = orderRepository.findById(1L);
        order =orderOpt.get();
        Assertions.assertEquals("finished", order.getStatus());

    }

    @Test
    public void deleteOrder() {
        orderRepository.deleteById(1L);
        Optional<Order>  order = orderRepository.findById(1L);
        assertTrue(order.isEmpty());

    }

}
