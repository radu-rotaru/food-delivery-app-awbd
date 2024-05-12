package web.javaproject.fooddeliveryapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import web.javaproject.fooddeliveryapp.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, Long> {
    Optional<Restaurant> findByEmail(String email);
    Optional<Restaurant> findById(Long id);
    void deleteById(Long id);
    Restaurant save(Restaurant restaurant);
    @Query("select r from Restaurant r where r.openingHours = ?1")
    List<Restaurant> findByOpeningHours(String openingHours);

}
