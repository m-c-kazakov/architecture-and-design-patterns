package com.otus.solid.first.war.of.tanks.exceptionHandling.excrptionHandlers;

import com.otus.solid.first.war.of.tanks.exceptionHandling.commands.QueueAdderCommand;
import com.otus.solid.first.war.of.tanks.exceptionHandling.commands.RetryCommand;
import com.otus.solid.first.war.of.tanks.exceptionHandling.context.ExceptionContext;
import com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions.CommandException;

public class CommandExceptionHandler implements ExceptionHandler{
    @Override
    public boolean isNeedToProcess(ExceptionContext exceptionContext) {
//        return CommandException.class.getCanonicalName().equals(exceptionContext.getCommand().getClass().getCanonicalName());
        return exceptionContext.getException() instanceof CommandException;
    }

    @Override
    public void processing(ExceptionContext exceptionContext) {
        RetryCommand retryCommand = RetryCommand.builder().exceptionContext(exceptionContext).numberOfAttempts(2).build();
        QueueAdderCommand queueAdderCommand = QueueAdderCommand.builder().actionQueue(exceptionContext.getActionsQueue()).action(retryCommand).build();
        queueAdderCommand.execute();
    }
}
