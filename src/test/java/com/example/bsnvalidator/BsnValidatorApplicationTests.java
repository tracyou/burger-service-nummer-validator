package com.example.bsnvalidator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BsnValidatorApplicationTests {

    BsnValidator bsnValidatorController = new BsnValidator();

    @Test
    void testNumberIsValid() {
        assertTrue(bsnValidatorController.isValid(("472002193")));
    }

    @Test
    void exceptionIsThrownWithShortNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bsnValidatorController.hasRightLength("1234567");
        });

        assertTrue(exception.getMessage().contains("BSN is too short. It should be at least 8."));
    }

    @Test
    void testNumberIsTooLong() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bsnValidatorController.hasRightLength("123456789156");
        });

        assertTrue(exception.getMessage().contains("BSN is too long. It shouldn't be longer than 9."));
    }

    @Test
    void numberPassesTheElevenProof() {
        assertTrue(bsnValidatorController.elevenProofCheck("472002193"));
    }

    @Test
    void numberFailsTheElevenProof() {
        assertFalse(bsnValidatorController.elevenProofCheck("123456789"));
    }

    @Test
    void numbersHaveNoSequence() {
        assertTrue(bsnValidatorController.hasNoOrders("123456789"));
    }
}
