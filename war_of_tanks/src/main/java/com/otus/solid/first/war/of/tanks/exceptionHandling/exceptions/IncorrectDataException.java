package com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions;

public class IncorrectDataException extends CommandException{

    public IncorrectDataException(String commandName, String message) {
        super(commandName, message);
    }
}
