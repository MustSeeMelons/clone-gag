package com.strautins.CloneGag.model.validators;

import com.strautins.CloneGag.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Checks for username uniqueness, returns TRUE if username is null or empty.
 */
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsernameConstraint, String> {

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
