package com.blog.xyz.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NotNullValidator.class)
public @interface NotNull {
    public String message() default "Value cannot be null or empty";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
