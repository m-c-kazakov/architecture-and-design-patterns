package com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions;

import lombok.Getter;

@Getter
public class CommandException extends RuntimeException{
    private final String commandName;

    public CommandException(Throwable cause, String commandName, String errorMessage) {
        super(errorMessage, cause);
        this.commandName = commandName;
    }

    public CommandException(String commandName, String errorMessage) {
        super(errorMessage);
        this.commandName = commandName;
    }
}
