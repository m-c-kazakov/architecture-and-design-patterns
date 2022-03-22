package com.otus.solid.first.war.of.tanks.exceptionHandling.excrptionHandlers;

import com.otus.solid.first.war.of.tanks.exceptionHandling.commands.ExceptionLogging;
import com.otus.solid.first.war.of.tanks.exceptionHandling.context.ExceptionContext;
import com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions.IncorrectDataException;

/**
 * Обработчик исключения, который ставит Команду, пишущую в лог в очередь Команд.
 */
public class IncorrectDataExceptionHandler implements ExceptionHandler {
    @Override
    public boolean isNeedToProcess(ExceptionContext exceptionContext) {
        return exceptionContext.getException() instanceof IncorrectDataException;
    }

    @Override
    public void processing(ExceptionContext exceptionContext) {
        exceptionContext.getActionsQueue().add(ExceptionLogging.builder().exceptionContext(exceptionContext).build());
    }
}
