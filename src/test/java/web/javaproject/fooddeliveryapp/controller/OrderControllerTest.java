package web.javaproject.fooddeliveryapp.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import web.javaproject.fooddeliveryapp.dto.CreateOrderDTO;
import web.javaproject.fooddeliveryapp.dto.OrderDTO;
import web.javaproject.fooddeliveryapp.exception.*;
import web.javaproject.fooddeliveryapp.mapper.OrderMapper;
import web.javaproject.fooddeliveryapp.model.Order;
import web.javaproject.fooddeliveryapp.service.OrderService;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderController orderController;

    @Test
    public void testCreateOrder_Success() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, 1L, new ArrayList<>(Arrays.asList(1L, 2L)));

        OrderDTO orderDTO = new OrderDTO();
        Order createdOrder = new Order();

        Mockito.when(orderService.createOrder(createOrderDTO)).thenReturn(createdOrder);
        Mockito.when(orderMapper.toDTO(createdOrder)).thenReturn(orderDTO);

        ResponseEntity<?> responseEntity = orderController.create(createOrderDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(orderDTO, responseEntity.getBody());
    }

    @Test
    public void testCreateOrder_RestaurantDoesNotExist() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, 1L, new ArrayList<>(Arrays.asList(1L, 2L)));

        Mockito.when(orderService.createOrder(createOrderDTO))
                .thenThrow(new RestaurantDoesNotExistException());

        ResponseEntity<?> responseEntity = orderController.create(createOrderDTO);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Error creating order: The restaurant does not exist.", responseEntity.getBody());
    }

    @Test
    public void testCreateOrder_ClientDoesNotExist() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, 1L, new ArrayList<>(Arrays.asList(1L, 2L)));

        Mockito.when(orderService.createOrder(createOrderDTO))
                .thenThrow(new ClientDoesNotExistException());

        ResponseEntity<?> responseEntity = orderController.create(createOrderDTO);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Error creating order: The client does not exist.", responseEntity.getBody());
    }

    @Test
    public void testCreateOrder_CourierDoesNotExist() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, 1L, new ArrayList<>(Arrays.asList(1L, 2L)));

        Mockito.when(orderService.createOrder(createOrderDTO))
                .thenThrow(new CourierDoesNotExistException());

        ResponseEntity<?> responseEntity = orderController.create(createOrderDTO);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Error creating order: The courier does not exist.", responseEntity.getBody());
    }

    @Test
    public void testCreateOrder_CourierNotAvailable() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, 1L, new ArrayList<>(Arrays.asList(1L, 2L)));

        Mockito.when(orderService.createOrder(createOrderDTO))
                .thenThrow(new CourierNotAvailableException());

        ResponseEntity<?> responseEntity = orderController.create(createOrderDTO);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Error creating order: The courier is not available.", responseEntity.getBody());
    }

    @Test
    public void testCreateOrder_DishDoesNotExist() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, 1L, new ArrayList<>(Arrays.asList(1L, 2L)));

        Mockito.when(orderService.createOrder(createOrderDTO))
                .thenThrow(new DishDoesNotExistException());

        ResponseEntity<?> responseEntity = orderController.create(createOrderDTO);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Error creating order: The dish does not exist.", responseEntity.getBody());
    }

    @Test
    public void testCreateOrder_DishNotOnTheMenu() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, 1L, new ArrayList<>(Arrays.asList(1L, 2L)));

        Mockito.when(orderService.createOrder(createOrderDTO))
                .thenThrow(new DishNotOnMenuException());

        ResponseEntity<?> responseEntity = orderController.create(createOrderDTO);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Error creating order: The dish was not on the menu.", responseEntity.getBody());
    }
}
