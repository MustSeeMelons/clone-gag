package com.strautins.CloneGag.model.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidImageValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidImageConstraint {
    String message() default "{com.strautins.CloneGag.constraints.image.not}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
