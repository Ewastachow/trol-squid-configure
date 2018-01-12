package trol.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TrolDomainValidator implements ConstraintValidator<TrolDomain,String> {
    private static final String DOMAIN_PATTERN =
            "\\b((?=[a-z0-9-]{1,63}\\.)(xn--)?[a-z0-9]+(-[a-z0-9]+)*\\.)+[a-z]{2,63}\\b";

    @Override
    public void initialize(TrolDomain constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null ? false : value.matches(DOMAIN_PATTERN);
    }
}
