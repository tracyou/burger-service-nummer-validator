package com.example.bsnvalidator.rest;

public class BsnValidatorController {
//    public boolean isValid(String validBsn) {
//        return false;
//    }

    public boolean hasNoOrders() {
        return false;
    }

//    public boolean isUnique(String validNumber) {
//        return false;
//    }

    public boolean hasRightLength(String bsn) {
        if (bsn.length() < 8 ){
            throw new IllegalArgumentException("BSN is too short. It should be at least 8.");
        } else if (bsn.length() > 9){
            throw new IllegalArgumentException("BSN is too long. It shouldn't be longer than 9.");
        }else {
            return true;
        }
    }


    public boolean elevenProofCheck(String bsn) {
//        if (bsn.length() == 8) {
//            bsn = "0" + bsn;
//        }

        int sum = 0;
        int[] weights = {9, 8, 7, 6, 5, 4, 3, 2, -1};

        for (int i = 0; i < 9; i++) {
            int digit = Integer.parseInt(String.valueOf(bsn.charAt(i)));
            sum += digit * weights[i];
        }

        return sum % 11 == 0;
    }
}
