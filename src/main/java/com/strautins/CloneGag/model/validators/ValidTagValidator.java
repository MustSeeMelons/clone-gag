package com.strautins.CloneGag.model.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ValidTagValidator implements ConstraintValidator<ValidTagConstraint, String> {

    private Pattern pattern = Pattern.compile("^.+(,.+)*$");

    @Override
    public void initialize(ValidTagConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        return pattern.matcher(s).matches();
    }
}
