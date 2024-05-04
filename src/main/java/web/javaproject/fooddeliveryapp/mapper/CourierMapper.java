package web.javaproject.fooddeliveryapp.mapper;

import org.mapstruct.Mapper;
import web.javaproject.fooddeliveryapp.dto.CourierDTO;
import web.javaproject.fooddeliveryapp.model.Courier;

@Mapper(componentModel = "spring")
public interface CourierMapper {
    CourierDTO toDTO(Courier courier);
}
