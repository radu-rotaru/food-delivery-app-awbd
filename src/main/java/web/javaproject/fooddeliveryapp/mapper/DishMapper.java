package web.javaproject.fooddeliveryapp.mapper;

import org.mapstruct.Mapper;
import web.javaproject.fooddeliveryapp.dto.CreateDishDTO;
import web.javaproject.fooddeliveryapp.dto.DishDTO;
import web.javaproject.fooddeliveryapp.model.Dish;

import java.util.List;

@Mapper (componentModel = "spring")
public interface DishMapper {
    DishDTO toDTO(Dish dish);
    List<DishDTO> toDTOsList(List<Dish> dishes);

    Dish toEntity(DishDTO updateDishDTO);

    Dish createDTOtoEntity(CreateDishDTO createDishDTO);
}
