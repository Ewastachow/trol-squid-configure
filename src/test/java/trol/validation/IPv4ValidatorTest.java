package trol.validation;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

import javax.validation.ConstraintValidatorContext;
import javax.validation.Valid;

import static org.junit.Assert.*;

public class IPv4ValidatorTest {

    private IPv4Validator validator = new IPv4Validator();

    @Test
    public void validate_emptyString() {
        assertEquals(false,validator.isValid("",null));
    }

    @Test
    public void validate_nullString() {
        assertEquals(false,validator.isValid(null,null));
    }

    @Test
    public void validate_tooShortAddress() {
        assertEquals(false,validator.isValid("1.2",null));
        assertEquals(false,validator.isValid("1.2.3",null));
        assertEquals(false,validator.isValid("1",null));
    }

    @Test
    public void validate_tooLongAddress() {
        assertEquals(false,validator.isValid("1.2.3.4.5",null));
        assertEquals(false,validator.isValid("1.2.3.4.5.6",null));
    }

    @Test
    public void validate_dotOnEnd() {
        assertEquals(false,validator.isValid("1.2.",null));
        assertEquals(false,validator.isValid("1.2.3.4.",null));
        assertEquals(false,validator.isValid("1.2.3.",null));
        assertEquals(false,validator.isValid(".",null));
    }

    @Test
    public void validate_tooHighNumber() {
        assertEquals(false,validator.isValid("1.256.",null));
        assertEquals(false,validator.isValid("1.2.3.256",null));
    }

    @Test
    public void validate_illegalCharacters() {
        assertEquals(false,validator.isValid("1.2. 3.4",null));
        assertEquals(false,validator.isValid("1.2.a3.4",null));
        assertEquals(false,validator.isValid("1.2.3.4 ",null));
    }

    @Test
    public void validate_validAddress() {
        assertEquals(true,validator.isValid("1.2.3.4",null));
        assertEquals(true,validator.isValid("255.255.255.254",null));
        assertEquals(true,validator.isValid("1.0.0.0",null));
    }
}