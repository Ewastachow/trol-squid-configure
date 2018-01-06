package trol.validation;

import org.junit.Test;

import static org.junit.Assert.*;

public class StrongPasswordValidatorTest {
    private StrongPasswordValidator validator = new StrongPasswordValidator();

    @Test
    public void validate_nullString(){
        assertEquals(false, validator.isValid(null, null));
    }

    @Test
    public void validate_emptyString(){
        assertEquals(false, validator.isValid("", null));
    }

    @Test
    public void validate_shortString(){
        assertEquals(false, validator.isValid("a", null));
        assertEquals(false, validator.isValid("abb5", null));
        assertEquals(false, validator.isValid("55ba5", null));
    }

    @Test
    public void validate_noDigitString(){
        assertEquals(false, validator.isValid("aaaaaaaaaaa", null));
    }

    @Test
    public void validate_validString(){
        assertEquals(true, validator.isValid("aaaaaaaaaa455", null));
        assertEquals(true, validator.isValid("333aaaaaa", null));
        assertEquals(true, validator.isValid("aa5aaa43aa5aaa", null));
        assertEquals(true, validator.isValid("aaaaa aaaaa 455", null));
        assertEquals(true, validator.isValid("abcde5", null));
    }

}