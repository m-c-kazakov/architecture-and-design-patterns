package com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions;

public class AnImpossibleActionException extends CommandException {
    public AnImpossibleActionException(Throwable cause, String commandName, String errorMessage) {
        super(cause, commandName, errorMessage);
    }

    public AnImpossibleActionException(String commandName, String errorMessage) {
        super(commandName, errorMessage);
    }
}
