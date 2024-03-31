package com.ICE.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniversityIdValidator implements ConstraintValidator<UniversityId,String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        int valueLength = s.length();
        if((valueLength == 11) && (s.charAt(4) == 'F') && (s.charAt(7) == 'F') && (s.startsWith("BT")))
        {
            return true;
        }
        return false;
    }
}
