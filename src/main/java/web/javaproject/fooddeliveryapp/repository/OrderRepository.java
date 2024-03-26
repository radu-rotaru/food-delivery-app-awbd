package web.javaproject.fooddeliveryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.javaproject.fooddeliveryapp.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
