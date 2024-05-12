package web.javaproject.fooddeliveryapp.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import web.javaproject.fooddeliveryapp.model.Order;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@ActiveProfiles("h2")
@Slf4j
public class OrderRepositoryH2Test {
    OrderRepository orderRepository;
    @Autowired
    OrderRepositoryH2Test(OrderRepository orderRepository) {this.orderRepository = orderRepository;}

    @Test
    public void findOrderByClient(){
        List<Order> orders = orderRepository.findByClientId(1L);
        assertTrue(orders.size() > 0);
        log.info("Find orders by client id...");
        orders.forEach(order -> log.info(order.getStatus()));
    }

    @Test
    public void findOrderByClientNotFound(){
        List<Order> orders = orderRepository.findByClientId(200L);
        assertEquals(0, orders.size());
        log.info("Find orders by client id - Not Found...");
        log.info("Orders not found: " + orders.isEmpty());
    }

    @Test
    public void findOrderByClientAndStatus(){
        List<Order> orders = orderRepository.findByClientIdAndStatus(1L, "processed");
        assertTrue(orders.size() > 0);
        log.info("Find orders by client and status...");
        orders.forEach(order -> log.info(order.getStatus()));
    }

    @Test
    public void findOrderByClientAndStatusNotFound(){
        List<Order> orders = orderRepository.findByClientIdAndStatus(1L, "unprocessed");
        assertEquals(false, orders.size() > 0);
        log.info("Find orders by client and status - Not Found...");
        log.info("Orders not found: " + orders.isEmpty());
    }
}
