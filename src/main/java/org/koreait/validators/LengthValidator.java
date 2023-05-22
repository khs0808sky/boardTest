package org.koreait.validators;

public interface LengthValidator {
    default void lengthCheck(String str, int max, RuntimeException e) {
        if (max > 0 && (str == null || str.length() > max)) {
            throw e;
        }
    }
}
