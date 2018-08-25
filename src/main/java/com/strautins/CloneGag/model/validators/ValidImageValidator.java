package com.strautins.CloneGag.model.validators;

import com.strautins.CloneGag.utils.ImageUtils;

import org.apache.commons.lang.ArrayUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidImageValidator implements ConstraintValidator<ValidImageConstraint, Byte[]> {

    @Override
    public void initialize(ValidImageConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Byte[] byteArray, ConstraintValidatorContext constraintValidatorContext) {
        if (byteArray == null || byteArray.length == 0) {
            return false;
        }

        return ImageUtils.getSupportedMime(ArrayUtils.toPrimitive(byteArray)) != null;
    }


}
