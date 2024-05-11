package web.javaproject.fooddeliveryapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.javaproject.fooddeliveryapp.dto.UserDto;
import web.javaproject.fooddeliveryapp.mapper.UserMapper;
import web.javaproject.fooddeliveryapp.model.security.Authority;
import web.javaproject.fooddeliveryapp.model.security.User;
import web.javaproject.fooddeliveryapp.repository.security.AuthorityRepository;
import web.javaproject.fooddeliveryapp.repository.security.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    UserRepository userRepository;
    AuthorityRepository authorityRepository;
    ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, UserMapper userMapper, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userMapper = userMapper;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto save (UserDto user) {
        Authority authority = authorityRepository.findByRole(user.getRole());
        User newUser = User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .associatedId(1L)
                .authority(authority)
                .build();
       User savedUser = userRepository.save(newUser);
        return modelMapper.map(userMapper.toDto(savedUser), UserDto.class);
    }
}
