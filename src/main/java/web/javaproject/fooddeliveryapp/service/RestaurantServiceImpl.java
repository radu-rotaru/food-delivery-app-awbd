package web.javaproject.fooddeliveryapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import web.javaproject.fooddeliveryapp.dto.RestaurantDTO;
import web.javaproject.fooddeliveryapp.exception.RestaurantAlreadyExistsException;
import web.javaproject.fooddeliveryapp.exception.RestaurantDoesNotExistException;
import web.javaproject.fooddeliveryapp.model.Restaurant;
import web.javaproject.fooddeliveryapp.repository.RestaurantRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService{
    RestaurantRepository restaurantRepository;

    ModelMapper modelMapper;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, ModelMapper modelMapper) {
        this.restaurantRepository = restaurantRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RestaurantDTO> findAll(){
        List<Restaurant> restaurants = new LinkedList<>();
        restaurantRepository.findAll(Sort.by("name")).iterator().forEachRemaining(restaurants::add);

        return restaurants.stream().map(restaurant -> modelMapper.map(restaurant, RestaurantDTO.class)).collect(Collectors.toList());
    }

    @Override
    public RestaurantDTO findById(Long l) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(l);
        if (!restaurantOptional.isPresent()) {
            throw new RestaurantDoesNotExistException();
            //throw new RuntimeException("Product not found!");
        }
        return modelMapper.map(restaurantOptional.get(), RestaurantDTO.class);
    }

    @Override
    public Restaurant getRestaurant(Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);

        if(restaurant.isEmpty()) {
            throw new RestaurantDoesNotExistException();
        }

        return restaurant.get();
    }

    public Restaurant getRestaurantByEmail(String email) {
        Optional<Restaurant> restaurant = restaurantRepository.findByEmail(email);

        if(restaurant.isEmpty()) {
            throw new RestaurantDoesNotExistException();
        }

        return restaurant.get();
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        Optional<Restaurant> existingRestaurant = restaurantRepository.findByEmail(restaurant.getEmail());

        if(existingRestaurant.isPresent()) {
            throw new RestaurantAlreadyExistsException();
        }

        return restaurantRepository.save(restaurant);
    }

    @Override
    public RestaurantDTO save (RestaurantDTO restaurant) {
        Restaurant savedRestaurant = restaurantRepository.save(modelMapper.map(restaurant, Restaurant.class));
        return modelMapper.map(savedRestaurant, RestaurantDTO.class);
    }

//    public void update(RestaurantDTO restaurantDTO) {
//        Optional<Restaurant> existingRestaurant = restaurantRepository.findById(restaurantDTO.getId());
//        if (!existingRestaurant.isPresent()) {
//            throw new RestaurantDoesNotExistException();
//        }
//        existingRestaurant.get().setName(restaurantDTO.getName());
//        existingRestaurant.get().setAddress(restaurantDTO.getAddress());
//        existingRestaurant.get().setOpeningHours(restaurantDTO.getOpeningHours());
//
//        restaurantRepository.save(existingRestaurant.get());
//
//    }

    @Override
    public void deleteById(Long id){restaurantRepository.deleteById(id);}
}
