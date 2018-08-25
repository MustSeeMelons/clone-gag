package com.strautins.CloneGag.model.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidTagValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTagConstraint {
    String message() default "{com.strautins.CloneGag.constraints.tags.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
