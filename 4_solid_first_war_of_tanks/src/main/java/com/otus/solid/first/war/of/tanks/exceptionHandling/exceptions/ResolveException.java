package com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions;

public class ResolveException extends RuntimeException{
    public ResolveException(String message) {
        super(message);
    }

    public ResolveException(String s, Exception exception) {
        super(s, exception);
    }
}
