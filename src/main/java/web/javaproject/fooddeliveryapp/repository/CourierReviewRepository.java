package web.javaproject.fooddeliveryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.javaproject.fooddeliveryapp.model.CourierReview;

import java.util.Optional;

public interface CourierReviewRepository extends JpaRepository<CourierReview, Long> {
    Optional<CourierReview> findByClientIdAndCourierIdAndOrderId(Long clientId, Long courierId, Long orderId);
}
