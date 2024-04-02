package web.javaproject.fooddeliveryapp.dto;

import java.util.List;

public class UpdateOrderDTO {
    private String status;
    private List<Long> dishIds;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Long> getDishIds() {
        return dishIds;
    }

    public void setDishIds(List<Long> dishIds) {
        this.dishIds = dishIds;
    }
}

