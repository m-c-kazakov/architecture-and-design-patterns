package com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions;

public class CollisionMoveException extends CommandException {
    public CollisionMoveException(String commandName, String errorMessage) {
        super(commandName, errorMessage);
    }
}
