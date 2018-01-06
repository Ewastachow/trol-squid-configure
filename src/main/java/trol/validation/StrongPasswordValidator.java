package trol.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {

    @Override
    public void initialize(StrongPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null ? false : (value.length() >= 6) && (value.matches(".*[0-9]+.*"));
    }

    public boolean isValid(String value) {
        return value == null ? false : (value.length() >= 6) && (value.matches(".*[0-9]+.*"));
    }
}
