package com.example.storyeverything.validation;

import com.example.storyeverything.annotation.LowerCase;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LowerCaseValidator implements ConstraintValidator<LowerCase, String> {

    @Override
    public void initialize(LowerCase constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true; // consider null as valid, or combine with @NotNull for required fields

        return value.equals(value.toLowerCase());
    }
}
