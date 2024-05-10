package web.javaproject.fooddeliveryapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import web.javaproject.fooddeliveryapp.dto.RestaurantDTO;
import web.javaproject.fooddeliveryapp.dto.UserDto;
import web.javaproject.fooddeliveryapp.model.Restaurant;
import web.javaproject.fooddeliveryapp.model.security.User;
import web.javaproject.fooddeliveryapp.repository.security.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto save (UserDto user) {
       User savedUser = userRepository.save(modelMapper.map(user, User.class));
        return modelMapper.map(savedUser, UserDto.class);
    }
}
