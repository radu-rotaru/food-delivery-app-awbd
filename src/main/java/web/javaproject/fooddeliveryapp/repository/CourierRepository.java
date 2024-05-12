package web.javaproject.fooddeliveryapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import web.javaproject.fooddeliveryapp.model.Courier;

import java.util.Optional;

public interface CourierRepository extends PagingAndSortingRepository<Courier, Long> {
    Optional<Courier> findByPhoneNumber(String phoneNumber);
    Optional<Courier> findFirstByAvailable(Boolean available);
    Optional<Courier> findById(Long id);
    void deleteById(Long id);
    Courier save(Courier courier);
}

