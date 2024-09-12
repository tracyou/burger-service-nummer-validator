package com.example.bsnvalidator;

import com.example.bsnvalidator.rest.BsnValidatorController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BsnValidatorApplicationTests {

    BsnValidatorController bsnValidatorController = new BsnValidatorController();

//    @Test
//    void testNumberIsValid() {
//        String validNumber = "123456789";
//        assertTrue(bsnValidatorController.isValid(validNumber));
//    }

//    @Test
//    void testNumberAlreadyExists() {
//        String validNumber= "123456789";
//        assertFalse(bsnValidatorController.isUnique(validNumber));
//    }

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
        String validNumber = "123456789";
        assertTrue(bsnValidatorController.hasNoOrders());
    }
}
