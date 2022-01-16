package com.otus.architecture.and.design.patterns.exceptions;

public class IncorrectValueException extends RuntimeException{

    public IncorrectValueException(String message) {
        super(message);
    }
}
