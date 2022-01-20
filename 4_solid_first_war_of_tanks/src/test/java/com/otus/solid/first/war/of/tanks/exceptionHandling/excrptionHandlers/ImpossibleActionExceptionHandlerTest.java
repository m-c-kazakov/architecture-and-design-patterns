package com.otus.solid.first.war.of.tanks.exceptionHandling.excrptionHandlers;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.exceptionHandling.commands.RetryCommand;
import com.otus.solid.first.war.of.tanks.exceptionHandling.context.ExceptionContext;
import com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions.ImpossibleActionException;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ImpossibleActionExceptionHandlerTest {


    @Test
    void isNeedToProcess() {
        ExceptionContext exceptionContext = ExceptionContext.builder()
                .exception(new ImpossibleActionException(ImpossibleActionException.class.getCanonicalName(), "Ошибка")).build();
        ImpossibleActionExceptionHandler impossibleActionExceptionHandler = new ImpossibleActionExceptionHandler();
        assertTrue(impossibleActionExceptionHandler.isNeedToProcess(exceptionContext));
    }

    @Test
    void processing() {
        ImpossibleActionExceptionHandler impossibleActionExceptionHandler = new ImpossibleActionExceptionHandler();

        Action command = new Action() {
            @Override
            public void execute() {

            }
        };
        LinkedList<Action> actionsQueue = new LinkedList<>();

        impossibleActionExceptionHandler.processing(ExceptionContext.builder().actionsQueue(actionsQueue).build());

        assertEquals(1, actionsQueue.size());
        assertEquals(actionsQueue.peek().getClass(), RetryCommand.class);
    }
}