package web.javaproject.fooddeliveryapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class UpdateOrderDTO {
    private String status;
    private List<Long> dishIds;

    public UpdateOrderDTO() {
    }

    public UpdateOrderDTO(String status, List<Long> dishIds) {
        this.status = status;
        this.dishIds = dishIds;
    }
}

