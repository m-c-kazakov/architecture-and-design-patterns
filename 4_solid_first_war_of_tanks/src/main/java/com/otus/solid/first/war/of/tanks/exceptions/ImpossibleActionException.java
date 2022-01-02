package com.otus.solid.first.war.of.tanks.exceptions;

public class ImpossibleActionException extends RuntimeException {
    public ImpossibleActionException(String message) {
        super(message);
    }
}
