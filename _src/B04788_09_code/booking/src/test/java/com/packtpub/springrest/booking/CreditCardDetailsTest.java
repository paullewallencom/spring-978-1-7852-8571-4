package com.packtpub.springrest.booking;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CreditCardDetailsTest {

    @Test
    public void testValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        CreditCardDetails creditCardDetails = new CreditCardDetails("John Doe", "4111-1111-1111-1111", "01/19", "111");
        Set<ConstraintViolation<CreditCardDetails>> violations = validator.validate(creditCardDetails);
        assertTrue(violations.isEmpty());

        // invalid card number
        creditCardDetails = new CreditCardDetails("John Doe", "11111111111111111", "01/19", "111");
        violations = validator.validate(creditCardDetails);
        assertEquals(1, violations.size());
        assertEquals("11111111111111111", violations.iterator().next().getInvalidValue());

        // invalid expiration
        creditCardDetails = new CreditCardDetails("John Doe", "4111-1111-1111-1111", "0119", "111");
        violations = validator.validate(creditCardDetails);
        assertEquals(1, violations.size());
        assertEquals("0119", violations.iterator().next().getInvalidValue());

        // invalid cvv
        creditCardDetails = new CreditCardDetails("John Doe", "4111-1111-1111-1111", "01/19", "abc");
        violations = validator.validate(creditCardDetails);
        assertEquals(1, violations.size());
        assertEquals("abc", violations.iterator().next().getInvalidValue());

        // null value
        creditCardDetails = new CreditCardDetails(null, "4111-1111-1111-1111", "01/19", "111");
        violations = validator.validate(creditCardDetails);
        assertEquals(1, violations.size());
        assertNull(violations.iterator().next().getInvalidValue());
    }
}