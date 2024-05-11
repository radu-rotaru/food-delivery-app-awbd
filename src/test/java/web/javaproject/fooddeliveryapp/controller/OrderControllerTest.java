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
import web.javaproject.fooddeliveryapp.dto.UpdateOrderDTO;
import web.javaproject.fooddeliveryapp.exception.*;
import web.javaproject.fooddeliveryapp.mapper.OrderMapper;
import web.javaproject.fooddeliveryapp.model.Order;
import web.javaproject.fooddeliveryapp.service.OrderService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
        private OrderController orderController;
//
//    @Test
//    public void testCreateOrder_Success() {
//        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, 1L, new ArrayList<>(Arrays.asList(1L, 2L)));
//
//        OrderDTO orderDTO = new OrderDTO();
//        Order createdOrder = new Order();
//
//        Mockito.when(orderService.createOrder(createOrderDTO)).thenReturn(createdOrder);
//        Mockito.when(orderMapper.toDTO(createdOrder)).thenReturn(orderDTO);
//
//        ResponseEntity<?> responseEntity = orderController.create(createOrderDTO);
//
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//        assertEquals(orderDTO, responseEntity.getBody());
//    }
//
//    @Test
//    public void testCreateOrder_RestaurantDoesNotExist() {
//        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, 1L, new ArrayList<>(Arrays.asList(1L, 2L)));
//
//        Mockito.when(orderService.createOrder(createOrderDTO))
//                .thenThrow(new RestaurantDoesNotExistException());
//
//        ResponseEntity<?> responseEntity = orderController.create(createOrderDTO);
//
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        assertEquals("Error creating order: The restaurant does not exist.", responseEntity.getBody());
//    }
//
//    @Test
//    public void testCreateOrder_ClientDoesNotExist() {
//        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, 1L, new ArrayList<>(Arrays.asList(1L, 2L)));
//
//        Mockito.when(orderService.createOrder(createOrderDTO))
//                .thenThrow(new ClientDoesNotExistException());
//
//        ResponseEntity<?> responseEntity = orderController.create(createOrderDTO);
//
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        assertEquals("Error creating order: The client does not exist.", responseEntity.getBody());
//    }
//
//    @Test
//    public void testCreateOrder_CourierDoesNotExist() {
//        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, 1L, new ArrayList<>(Arrays.asList(1L, 2L)));
//
//        Mockito.when(orderService.createOrder(createOrderDTO))
//                .thenThrow(new CourierDoesNotExistException());
//
//        ResponseEntity<?> responseEntity = orderController.create(createOrderDTO);
//
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        assertEquals("Error creating order: The courier does not exist.", responseEntity.getBody());
//    }
//
//    @Test
//    public void testCreateOrder_CourierNotAvailable() {
//        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, 1L, new ArrayList<>(Arrays.asList(1L, 2L)));
//
//        Mockito.when(orderService.createOrder(createOrderDTO))
//                .thenThrow(new CourierNotAvailableException());
//
//        ResponseEntity<?> responseEntity = orderController.create(createOrderDTO);
//
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        assertEquals("Error creating order: The courier is not available.", responseEntity.getBody());
//    }
//
//    @Test
//    public void testCreateOrder_DishDoesNotExist() {
//        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, 1L, new ArrayList<>(Arrays.asList(1L, 2L)));
//
//        Mockito.when(orderService.createOrder(createOrderDTO))
//                .thenThrow(new DishDoesNotExistException());
//
//        ResponseEntity<?> responseEntity = orderController.create(createOrderDTO);
//
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        assertEquals("Error creating order: The dish does not exist.", responseEntity.getBody());
//    }
//
//    @Test
//    public void testCreateOrder_DishNotOnTheMenu() {
//        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, 1L, new ArrayList<>(Arrays.asList(1L, 2L)));
//
//        Mockito.when(orderService.createOrder(createOrderDTO))
//                .thenThrow(new DishNotOnMenuException());
//
//        ResponseEntity<?> responseEntity = orderController.create(createOrderDTO);
//
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        assertEquals("Error creating order: The dish was not on the menu.", responseEntity.getBody());
//    }

//    @Test
//    public void testGetOrderById() {
//        Long orderId = 1L;
//        Order order = new Order();
//        OrderDTO orderDTO = new OrderDTO();
//        Mockito.when(orderService.getOrder(orderId)).thenReturn(Optional.of(order);
//        Mockito.when(orderMapper.toDTO(order)).thenReturn(orderDTO);
//
//        ResponseEntity<?> responseEntity = orderController.getOrderById(orderId);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(orderDTO, responseEntity.getBody());
//    }
//
//    @Test
//    public void testGetOrderById_NotFound() {
//        Long orderId = 1L;
//        Mockito.when(orderService.getOrder(orderId)).thenReturn(Optional.empty());
//
//        ResponseEntity<?> responseEntity = orderController.getOrderById(orderId);
//
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//    }

//    @Test
//    public void testGetAllOrdersForClient() {
//        Long clientId = 1L;
//        String status = "pending";
//        List<Order> orders = new ArrayList<>();
//        List<OrderDTO> orderDTOs = new ArrayList<>();
//        Mockito.when(orderService.getAllOrders(clientId, status)).thenReturn(orders);
//        Mockito.when(orderMapper.toDTOsList(orders)).thenReturn(orderDTOs);
//
//        ResponseEntity<?> responseEntity = orderController.getAllOrdersForClient(clientId, status);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(orderDTOs, responseEntity.getBody());
//    }

//    @Test
//    public void testUpdateOrder() {
//        Long orderId = 1L;
//        UpdateOrderDTO updateOrderDTO = new UpdateOrderDTO();
//        Order updatedOrder = new Order();
//        OrderDTO updatedOrderDTO = new OrderDTO();
//        Mockito.when(orderService.updateOrder(orderId, updateOrderDTO)).thenReturn(updatedOrder);
//        Mockito.when(orderMapper.toDTO(updatedOrder)).thenReturn(updatedOrderDTO);
//
//        ResponseEntity<?> responseEntity = orderController.updateOrder(orderId, updateOrderDTO);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(updatedOrderDTO, responseEntity.getBody());
//    }

//    @Test
//    public void testDeleteOrder() {
//        Long orderId = 1L;
//
//        ResponseEntity<?> responseEntity = orderController.deleteOrder(orderId);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals("Order deleted successfully", responseEntity.getBody());
//        verify(orderService, times(1)).deleteOrder(orderId);
//    }
//
//    @Test
//    public void testDeleteOrder_Exception() {
//        Long orderId = 1L;
//        doThrow(new RuntimeException("Order not found")).when(orderService).deleteOrder(orderId);
//
//        ResponseEntity<?> responseEntity = orderController.deleteOrder(orderId);
//
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        assertEquals("Error deleting order: Order not found", responseEntity.getBody());
//    }
}
