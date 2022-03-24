package com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions;

public class ImpossibleActionException extends CommandException {
    public ImpossibleActionException(String commandName, String message) {
        super(commandName, message);
    }
}
