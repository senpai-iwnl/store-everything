package com.example.storyeverything.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.example.storyeverything.validation.LowerCaseValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = LowerCaseValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface LowerCase {

    String message() default "The field must contain only lowercase letters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
