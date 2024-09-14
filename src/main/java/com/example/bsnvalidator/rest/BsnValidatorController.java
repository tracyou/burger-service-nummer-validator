package com.example.bsnvalidator.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class BsnValidatorController {
    public boolean isValid(String validBsn) {
        return hasRightLength(validBsn) & elevenProofCheck(validBsn) & hasNoOrders(validBsn);
    }

    public boolean hasRightLength(String bsn) {
        if (bsn.length() < 8) {
            throw new IllegalArgumentException("BSN is too short. It should be at least 8.");
        } else if (bsn.length() > 9) {
            throw new IllegalArgumentException("BSN is too long. It shouldn't be longer than 9.");
        } else {
            return true;
        }
    }


    public boolean elevenProofCheck(String bsn) {
        if (bsn.length() == 8) {
            bsn = "0" + bsn;
        }

        int sum = 0;
        int[] weights = {9, 8, 7, 6, 5, 4, 3, 2, -1};

        for (int i = 0; i < 9; i++) {
            int digit = Integer.parseInt(String.valueOf(bsn.charAt(i)));
            System.out.println(digit);
            sum += digit * weights[i];
        }

        return sum % 11 == 0;
    }

    public boolean hasNoOrders(String validNumber) {
        int length = validNumber.length();
        int[] intArray = new int[length];

        for (int i = 0; i < length; i++) {
            try {
                intArray[i] = Integer.parseInt(String.valueOf(validNumber.charAt(i)));
            } catch (NumberFormatException e) {
                System.err.println("Invalid character at index " + i + ": " + validNumber.charAt(i));
            }
        }

        return hasSequence(intArray);
    }

    public static boolean hasSequence(int[] arr) {

        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 2; i++) {
            if (arr[i] == arr[i + 1] - 1 &&
                    arr[i + 1] == arr[i + 2] - 1) {
                return true;
            }
        }

        return false;
    }
}
