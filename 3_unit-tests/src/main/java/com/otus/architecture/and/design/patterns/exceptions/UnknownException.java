package com.otus.architecture.and.design.patterns.exceptions;

public class UnknownException extends RuntimeException {
    public UnknownException(String errorMessage) {
        super(errorMessage);
    }
}
