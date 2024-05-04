package web.javaproject.fooddeliveryapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import web.javaproject.fooddeliveryapp.dto.CreateCourierReviewDTO;
import web.javaproject.fooddeliveryapp.exception.*;
import web.javaproject.fooddeliveryapp.mapper.CourierReviewMapper;
import web.javaproject.fooddeliveryapp.model.Client;
import web.javaproject.fooddeliveryapp.model.Courier;
import web.javaproject.fooddeliveryapp.model.CourierReview;
import web.javaproject.fooddeliveryapp.model.Order;
import web.javaproject.fooddeliveryapp.repository.CourierReviewRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourierReviewServiceTest {

    @Mock
    private CourierReviewRepository courierReviewRepository;

    @Mock
    private ClientService clientService;

    @Mock
    private CourierService courierService;

    @Mock
    private OrderService orderService;

    @Mock
    private CourierReviewMapper courierReviewMapper;

    @InjectMocks
    private CourierReviewService courierReviewService;

    @Test
    void createCourierReview_Success() {
        CreateCourierReviewDTO createCourierReviewDTO = new CreateCourierReviewDTO(1L, 1L, 1L, 4);

        Client client = Mockito.mock(Client.class);
        Courier courier = Mockito.mock(Courier.class);
        Order order = Mockito.mock(Order.class);

        when(clientService.getClient(createCourierReviewDTO.getClientId())).thenReturn(client);
        when(courierService.getCourier(createCourierReviewDTO.getCourierId())).thenReturn(Optional.of(courier));
        when(orderService.getOrder(createCourierReviewDTO.getOrderId())).thenReturn(order);
        when(order.getClient()).thenReturn(client);
        when(order.getCourier()).thenReturn(courier);

        CourierReview savedCourierReview = new CourierReview(createCourierReviewDTO.getStars(), client, courier, order);
        when(courierReviewMapper.toCourierReviewFromCreated(createCourierReviewDTO)).thenReturn(savedCourierReview);
        when(courierReviewRepository.save(any(CourierReview.class))).thenReturn(savedCourierReview);

        CourierReview result = courierReviewService.createCourierReview(createCourierReviewDTO);

        assertNotNull(result);
        assertEquals(savedCourierReview, result);
        verify(clientService, times(1)).getClient(createCourierReviewDTO.getClientId());
        verify(courierService, times(1)).getCourier(createCourierReviewDTO.getCourierId());
        verify(orderService, times(1)).getOrder(createCourierReviewDTO.getOrderId());
        verify(courierReviewRepository, times(1)).save(any(CourierReview.class));
    }

    @Test
    void createCourierReview_ClientDoesNotExist_ThrowsException() {
        CreateCourierReviewDTO createCourierReviewDTO = new CreateCourierReviewDTO(1L, 1L, 1L, 4);

        when(clientService.getClient(createCourierReviewDTO.getClientId())).thenThrow(ClientDoesNotExistException.class);

        assertThrows(ClientDoesNotExistException.class,
                () -> courierReviewService.createCourierReview(createCourierReviewDTO));

        verify(courierService, never()).getCourier(anyLong());
        verify(orderService, never()).getOrder(anyLong());
        verify(courierReviewRepository, never()).save(any(CourierReview.class));
    }

    @Test
    void createCourierReview_CourierDoesNotExist_ThrowsException() {
        CreateCourierReviewDTO createCourierReviewDTO = new CreateCourierReviewDTO(1L, 1L, 1L, 4);

        when(clientService.getClient(createCourierReviewDTO.getClientId())).thenReturn(new Client());
        when(courierService.getCourier(createCourierReviewDTO.getCourierId())).thenReturn(Optional.empty());

        assertThrows(CourierDoesNotExistException.class,
                () -> courierReviewService.createCourierReview(createCourierReviewDTO));

        verify(orderService, never()).getOrder(anyLong());
        verify(courierReviewRepository, never()).save(any(CourierReview.class));
    }

    @Test
    void createCourierReview_OrderDoesNotExist_ThrowsException() {
        CreateCourierReviewDTO createCourierReviewDTO = new CreateCourierReviewDTO(1L, 1L, 1L, 4);

        when(clientService.getClient(createCourierReviewDTO.getClientId())).thenReturn(new Client());
        when(courierService.getCourier(createCourierReviewDTO.getCourierId())).thenReturn(Optional.of(new Courier()));
        when(orderService.getOrder(createCourierReviewDTO.getOrderId())).thenThrow(OrderDoesNotExistException.class);

        assertThrows(OrderDoesNotExistException.class,
                () -> courierReviewService.createCourierReview(createCourierReviewDTO));

        verify(courierReviewRepository, never()).save(any(CourierReview.class));
    }

    @Test
    void createCourierReview_OrderIsNotValid_ThrowsException() {
        CreateCourierReviewDTO createCourierReviewDTO = new CreateCourierReviewDTO(1L, 1L, 1L, 4);

        Client client = Mockito.mock(Client.class);
        Courier courier = Mockito.mock(Courier.class);
        Order order = Mockito.mock(Order.class);

        when(clientService.getClient(createCourierReviewDTO.getClientId())).thenReturn(client);
        when(courierService.getCourier(createCourierReviewDTO.getCourierId())).thenReturn(Optional.of(courier));
        when(orderService.getOrder(createCourierReviewDTO.getOrderId())).thenReturn(order);

        assertThrows(OrderIsNotValidException.class,
                () -> courierReviewService.createCourierReview(createCourierReviewDTO));

        verify(courierReviewRepository, never()).save(any(CourierReview.class));
    }

    @Test
    void createCourierReview_ReviewAlreadyExists() {
        CreateCourierReviewDTO createCourierReviewDTO = new CreateCourierReviewDTO(1L, 1L, 1L, 4);

        Client client = Mockito.mock(Client.class);
        Courier courier = Mockito.mock(Courier.class);
        Order order = Mockito.mock(Order.class);
        CourierReview courierReview = new CourierReview();

        when(clientService.getClient(createCourierReviewDTO.getClientId())).thenReturn(client);
        when(courierService.getCourier(createCourierReviewDTO.getCourierId())).thenReturn(Optional.of(courier));
        when(orderService.getOrder(createCourierReviewDTO.getOrderId())).thenReturn(order);
        when(client.getId()).thenReturn(1L);
        when(courier.getId()).thenReturn(1L);
        when(order.getId()).thenReturn(1L);
        when(courierReviewRepository.findByClientIdAndCourierIdAndOrderId(client.getId(), courier.getId(), order.getId())).thenReturn(Optional.of(courierReview));

        assertThrows(CourierReviewAlreadyExistsException.class,
                () -> courierReviewService.createCourierReview(createCourierReviewDTO));

        verify(courierReviewRepository, never()).save(any(CourierReview.class));
    }
}
