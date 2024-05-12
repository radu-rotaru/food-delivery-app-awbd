package web.javaproject.fooddeliveryapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import web.javaproject.fooddeliveryapp.dto.CourierReviewDTO;
import web.javaproject.fooddeliveryapp.exception.CourierDoesNotExistException;
import web.javaproject.fooddeliveryapp.mapper.CourierReviewMapper;
import web.javaproject.fooddeliveryapp.model.Courier;
import web.javaproject.fooddeliveryapp.model.CourierReview;
import web.javaproject.fooddeliveryapp.repository.CourierReviewRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourierReviewServiceTest {

    @Mock
    private CourierReviewRepository courierReviewRepository;

    @Mock
    private CourierReviewMapper courierReviewMapper;

    @Mock
    private ClientService clientService;

    @Mock
    private CourierService courierService;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private CourierReviewService courierReviewService;
    @Test
    public void getCourierReviewsByCourierId_WhenCourierExists_ReturnsListOfCourierReviews() {
        Long courierId = 1L;
        Courier courier = new Courier();
        courier.setId(courierId);
        List<CourierReview> expectedCourierReviews = new ArrayList<>();
        expectedCourierReviews.add(new CourierReview());

        when(courierService.getCourier(courierId)).thenReturn(Optional.of(courier));
        when(courierReviewRepository.findByCourierId(courierId)).thenReturn(expectedCourierReviews);

        List<CourierReview> actualCourierReviews = courierReviewService.getCourierReviewsByCourierId(courierId);

        assertEquals(expectedCourierReviews.size(), actualCourierReviews.size());
        verify(courierService, times(1)).getCourier(courierId);
        verify(courierReviewRepository, times(1)).findByCourierId(courierId);
    }

    @Test
    public void getCourierReviewsByCourierId_WhenCourierDoesNotExist_ThrowsException() {
        Long courierId = 1L;

        when(courierService.getCourier(courierId)).thenReturn(Optional.empty());

        assertThrows(CourierDoesNotExistException.class,
                () -> courierReviewService.getCourierReviewsByCourierId(courierId));

        verify(courierService, times(1)).getCourier(courierId);
        verifyNoInteractions(courierReviewRepository);
    }

    @Test
    public void getCourierReview_WhenCourierReviewExists_ReturnsCourierReview() {
        Long reviewId = 1L;
        CourierReview expectedCourierReview = new CourierReview();

        when(courierReviewRepository.findById(reviewId)).thenReturn(Optional.of(expectedCourierReview));

        CourierReview actualCourierReview = courierReviewService.getCourierReview(reviewId);

        assertNotNull(actualCourierReview);
        assertEquals(expectedCourierReview, actualCourierReview);
        verify(courierReviewRepository, times(1)).findById(reviewId);
    }

    @Test
    public void getCourierReview_WhenCourierReviewDoesNotExist_ThrowsException() {
        Long reviewId = 1L;

        when(courierReviewRepository.findById(reviewId)).thenReturn(Optional.empty());

        assertThrows(CourierDoesNotExistException.class,
                () -> courierReviewService.getCourierReview(reviewId));

        verify(courierReviewRepository, times(1)).findById(reviewId);
    }

    @Test
    public void save_ValidCourierReviewDTO_ReturnsSavedCourierReviewDTO() {
        CourierReviewDTO courierReviewDTO = new CourierReviewDTO();
        CourierReview savedCourierReview = new CourierReview();
        CourierReviewDTO savedCourierReviewDTO = new CourierReviewDTO();

        when(courierReviewMapper.toCourierReview(courierReviewDTO)).thenReturn(savedCourierReview);
        when(courierReviewRepository.save(savedCourierReview)).thenReturn(savedCourierReview);
        when(courierReviewMapper.toDto(savedCourierReview)).thenReturn(savedCourierReviewDTO);

        CourierReviewDTO actualSavedCourierReviewDTO = courierReviewService.save(courierReviewDTO);

        assertNotNull(actualSavedCourierReviewDTO);
        assertEquals(savedCourierReviewDTO, actualSavedCourierReviewDTO);
        verify(courierReviewMapper, times(1)).toCourierReview(courierReviewDTO);
        verify(courierReviewRepository, times(1)).save(savedCourierReview);
        verify(courierReviewMapper, times(1)).toDto(savedCourierReview);
    }

    @Test
    public void deleteById_ValidReviewId_DeletesCourierReview() {
        Long reviewId = 1L;

        courierReviewService.deleteById(reviewId);

        verify(courierReviewRepository, times(1)).deleteById(reviewId);
    }
}
