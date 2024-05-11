package web.javaproject.fooddeliveryapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import web.javaproject.fooddeliveryapp.model.Restaurant;

import java.util.Optional;

public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, Long> {
    Optional<Restaurant> findByEmail(String email);
    Optional<Restaurant> findById(Long id);
    void deleteById(Long id);
    Restaurant save(Restaurant restaurant);

}
