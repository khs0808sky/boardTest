package org.koreait.validators;

public interface Validator<T> extends RequiredValidator, LengthValidator {
    void check(T t);
}
