package com.example.storyeverything.validation;

import com.example.storyeverything.annotation.StartsWithCapital;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StartsWithCapitalValidator implements ConstraintValidator<StartsWithCapital, String> {

    @Override
    public void initialize(StartsWithCapital constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return Character.isUpperCase(value.charAt(0));
    }
}
