package com.example.bsnvalidator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BsnValidatorApplicationTests {

    BsnValidator bsnValidator = new BsnValidator();

    @Test
    void testNumberIsValid() {
        String validNumber = "123456789";
        assertTrue(bsnValidator.isValid(validNumber));
    }

    @Test
    void testNumberAlreadyExists() {
        String validNumber= "123456789";
        assertFalse(bsnValidator.isValid(validNumber));
    }

    @Test
    void testNumberIsTooShort() {
        String shortNumber = "1234567";
        assertFalse(bsnValidator.isValid(shortNumber));
    }

    @Test
    void testNumberIsTooLong() {
        String longNumber = "123456789156";
        assertFalse(bsnValidator.isValid(longNumber));
    }

    @Test
    void testNumberPassesTheElevenProof() {
        String validNumber = "123456789";
        assertTrue(bsnValidator.passesProof());
    }

    @Test
    void testNumberHasNoOrders() {
        String validNumber = "123456789";
        assertTrue(bsnValidator.hasNoOrders());
    }
}
