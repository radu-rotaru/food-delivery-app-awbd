package web.javaproject.fooddeliveryapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import web.javaproject.fooddeliveryapp.dto.RestaurantDTO;
import web.javaproject.fooddeliveryapp.model.Restaurant;
import web.javaproject.fooddeliveryapp.repository.RestaurantRepository;

import java.util.LinkedList;
import java.util.List;
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
}
