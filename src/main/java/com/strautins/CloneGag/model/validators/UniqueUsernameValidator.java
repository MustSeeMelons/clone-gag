package com.strautins.CloneGag.model.validators;

import com.strautins.CloneGag.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Checks for username uniqueness, returns TRUE if username is null or empty.
 * <p>
 * DOES NOT WORK AS @Autowired DOES NOT WORK IN VALIDATORS.
 */
@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsernameConstraint, String> {

    // TODO Figure out why @Autowired does not work in validators?.
    @Autowired
    UserService userService;

    @Override
    public void initialize(UniqueUsernameConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (username == null || username.isEmpty()) {
            return true;
        }
        return !userService.isUsernameTaken(username);
    }
}
