package com.ICE.Validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniversityIdValidator.class)
public @interface UniversityId {


    String message() default "University Id should follow the pattern 'BT(Admission Year - 24)F(Two digit Branch code)F(Your roll number - 000)'";

    Class<?> [] groups() default { };


    Class<? extends Payload>[] payload() default { };
}
