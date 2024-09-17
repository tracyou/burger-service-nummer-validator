package com.example.bsnvalidator;

import org.springframework.stereotype.Repository;


@Repository
public class BsnValidator {
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
            sum += digit * weights[i];
        }

        if (sum % 11 != 0) {
            throw new IllegalArgumentException("Number doesn't pass 11 proof.");
        }

        return true;
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

        if (hasSequence(intArray)) {
            throw new IllegalArgumentException("Number has an apparent sequence.");
        }

        return false;
    }

    public static boolean hasSequence(int[] arr) {

        for (int i = 0; i < arr.length - 2; i++) {
            if (arr[i] == arr[i + 1] - 1 &&
                    arr[i] == arr[i + 2]) {
                return true;
            }
        }

        for (int i = 0; i < arr.length - 2; i++) {
            int first = arr[i];
            int second = arr[i + 1];
            int third = arr[i + 2];

            if (second - first == 1 && third - second == 1) {
                return true;
            }
            if (first - second == 1 && second - third == 1) {
                return true;
            }
        }

        return false;
    }
}
