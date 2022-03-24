package com.otus.solid.first.war.of.tanks.exceptionHandling.excrptionHandlers;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.exceptionHandling.commands.ExceptionLogging;
import com.otus.solid.first.war.of.tanks.exceptionHandling.context.ExceptionContext;
import com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions.IncorrectDataException;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class IncorrectDataExceptionHandlerTest {

    @Test
    void isNeedToProcess() {
        IncorrectDataExceptionHandler incorrectDataExceptionHandler = new IncorrectDataExceptionHandler();
        assertTrue(incorrectDataExceptionHandler.isNeedToProcess(ExceptionContext.builder().exception(new IncorrectDataException(IncorrectDataException.class.getCanonicalName(), "LocationState2d содержит не корректнче данные")).build()));
    }

    @Test
    void processing() {
        IncorrectDataExceptionHandler incorrectDataExceptionHandler = new IncorrectDataExceptionHandler();
        LinkedList<Action> actionsQueue = new LinkedList<>();
        Action command = new Action() {
            @Override
            public void execute() {

            }
        };
        incorrectDataExceptionHandler.processing(
                ExceptionContext
                        .builder()
                        .command(command)
                        .actionsQueue(actionsQueue)
                        .build());


        assertEquals(1, actionsQueue.size());
        assertTrue(actionsQueue.peek().getClass().equals(ExceptionLogging.class));
    }
}