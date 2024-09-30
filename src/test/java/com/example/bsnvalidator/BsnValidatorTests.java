package com.example.bsnvalidator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BsnValidatorTests {

    BsnValidator bsnValidatorController = new BsnValidator();

    @Test
    void testNumberIsValid() {
        assertTrue(bsnValidatorController.isValid(("474862872")));
    }

    @Test
    void exceptionIsThrownWithShortNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bsnValidatorController.hasRightLength("12345678");
        });

        assertTrue(exception.getMessage().contains("BSN is too short. It should be at least 9."));
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
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bsnValidatorController.elevenProofCheck("123456789");
        });

        assertTrue(exception.getMessage().contains("Number doesn't pass 11 proof."));
    }

    @Test
    void numbersHaveNoSequence() {
        assertTrue(bsnValidatorController.hasNoOrders("474862872"));
    }

    @Test
    void numbersHaveASequence() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bsnValidatorController.hasNoOrders("111111111");
        });

        assertTrue(exception.getMessage().contains("Number has an apparent sequence."));
    }
}
