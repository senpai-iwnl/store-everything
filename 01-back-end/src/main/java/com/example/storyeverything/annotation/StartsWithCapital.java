package com.example.storyeverything.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.example.storyeverything.validation.StartsWithCapitalValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = StartsWithCapitalValidator.class)
public @interface StartsWithCapital {

    String message() default "The field must start with a capital letter.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}