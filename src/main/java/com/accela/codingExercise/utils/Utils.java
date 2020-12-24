package com.accela.codingExercise.utils;

public class Utils {
    public static boolean isInteger(String option) {
        if (option == null) {
            return false;
        }
        try {
            Integer d = Integer.parseInt(option);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
