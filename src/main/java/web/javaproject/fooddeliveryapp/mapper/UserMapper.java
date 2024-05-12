package web.javaproject.fooddeliveryapp.mapper;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import web.javaproject.fooddeliveryapp.dto.UserDto;
import web.javaproject.fooddeliveryapp.model.security.Authority;
import web.javaproject.fooddeliveryapp.model.security.User;
import web.javaproject.fooddeliveryapp.repository.security.AuthorityRepository;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    private final AuthorityRepository authorityRepository;


    public UserMapper(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        if (user != null && user.getAuthorities() != null && !user.getAuthorities().isEmpty())  {
            String role = user.getAuthorities().stream().map(Authority::getRole).collect(Collectors.joining(" "));
            userDto.setRole(role);
        }
        return userDto;
    }

    public User toUser(UserDto userDTO){
        User user = new User();
        Authority authority = authorityRepository.findByRole(userDTO.getRole());
        if (authority != null) {
            user.addAuthority(authority);
        }
        return user;
    }
}

