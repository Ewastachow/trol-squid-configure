package trol.validation;

import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.Assert.*;

public class IPv4ValidatorTest {

    @Test
    public void isValid() {
        IPv4Validator validator = new IPv4Validator();
        String address = "";

        Errors errors = new BeanPropertyBindingResult(invalidAddress, "invalidAddress");
        assertEquals(false,isValid(););
    }
}