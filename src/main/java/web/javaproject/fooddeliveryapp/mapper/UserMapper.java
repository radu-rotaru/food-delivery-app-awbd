package web.javaproject.fooddeliveryapp.mapper;

import org.mapstruct.Mapper;
import web.javaproject.fooddeliveryapp.dto.UserDto;
import web.javaproject.fooddeliveryapp.model.security.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto (User user);
    User toUser(UserDto userDTO);
}
