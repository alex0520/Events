package com.alozano.partnerships.interview.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ValidationErrorBuilder {

    public static ValidationError fromBindingErrors(Errors errors) {
        ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");
        for (ObjectError objectError : errors.getAllErrors()) {
            if(objectError instanceof FieldError){
                FieldError fieldError = (FieldError) objectError;
                error.addValidationError(String.format("%s: %s",fieldError.getField(),objectError.getDefaultMessage()));
            }
        }
        return error;
    }
}