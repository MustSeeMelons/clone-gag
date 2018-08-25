package com.strautins.CloneGag.model.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsernameConstraint {
    String message() default "{com.strautins.CloneGag.model.register.username}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
