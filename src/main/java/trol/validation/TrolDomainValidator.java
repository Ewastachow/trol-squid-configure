package trol.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TrolDomainValidator implements ConstraintValidator<TrolDomain,String> {
    @Override
    public void initialize(TrolDomain constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("^[a-zA-Z0-9\\.]+[a-zA-Z0-9]$");
    }
}
