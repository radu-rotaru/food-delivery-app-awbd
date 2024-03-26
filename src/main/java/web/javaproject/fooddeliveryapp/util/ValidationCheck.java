package web.javaproject.fooddeliveryapp.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ValidationCheck {
    public static String extractValidationErrorMessage(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder("Validation errors: \n");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage.append(fieldError.getDefaultMessage()).append("\n");
        }
        return errorMessage.toString().trim();
    }
}