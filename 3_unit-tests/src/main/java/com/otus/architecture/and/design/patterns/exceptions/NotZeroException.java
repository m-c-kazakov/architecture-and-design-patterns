package com.otus.architecture.and.design.patterns.exceptions;

public class NotZeroException extends RuntimeException {
    public NotZeroException(String errorMessage) {
        super(errorMessage);
    }
}
