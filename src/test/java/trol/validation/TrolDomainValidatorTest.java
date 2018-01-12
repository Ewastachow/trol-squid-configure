package trol.validation;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrolDomainValidatorTest {
    private TrolDomainValidator validator = new TrolDomainValidator();

    @Test
    public void validate_nullDomain() {
        assertEquals(false,validator.isValid(null,null));
    }

    @Test
    public void validate_emptyDomain() {
        assertEquals(false,validator.isValid("",null));
    }

    @Test
    public void validate_withoutDot() {
        assertEquals(false,validator.isValid("google",null));
        assertEquals(false,validator.isValid("gmail",null));
    }

    @Test
    public void validate_dotOnBegining() {
        assertEquals(false,validator.isValid(".google",null));
        assertEquals(false,validator.isValid(".",null));
        assertEquals(false,validator.isValid(".google.com",null));
    }

    @Test
    public void validate_dotOnEnd() {
        assertEquals(false,validator.isValid("google.",null));
        assertEquals(false,validator.isValid("google.com.",null));
    }

    @Test
    public void validate_multipleDots() {
        assertEquals(false,validator.isValid("google..",null));
        assertEquals(false,validator.isValid("..google...com...",null));
        assertEquals(false,validator.isValid("google..com",null));
        assertEquals(false,validator.isValid("mail..google.com",null));
    }

    @Test
    public void validate_validSimpleDomains() {
        assertEquals(true,validator.isValid("google.com",null));
        assertEquals(true,validator.isValid("a.pl",null));
        assertEquals(true,validator.isValid("google.pl",null));
    }

    @Test
    public void validate_validLongDomains() {
        assertEquals(true,validator.isValid("mail.google.com",null));
        assertEquals(true,validator.isValid("kis.agh.edu.pl",null));
    }
}