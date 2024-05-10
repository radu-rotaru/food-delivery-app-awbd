package web.javaproject.fooddeliveryapp.dto;

public class UpdateDishDTO extends DishDTO {
    private Long id;

    public UpdateDishDTO() {
    }

    public UpdateDishDTO(String name, int quantity, float price,  Long id) {
        super(name, quantity, price);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
