package com.otus.solid.first.war.of.tanks.exceptionHandling.excrptionHandlers;

import com.otus.solid.first.war.of.tanks.exceptionHandling.commands.RetryCommand;
import com.otus.solid.first.war.of.tanks.exceptionHandling.context.ExceptionContext;
import com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions.ImpossibleActionException;


/**
 * Обработчик исключения, который ставит в очередь Команду - повторитель команды, выбросившей исключение
 */
public class ImpossibleActionExceptionHandler implements ExceptionHandler{
    @Override
    public boolean isNeedToProcess(ExceptionContext exceptionContext) {
        return exceptionContext.getException() instanceof ImpossibleActionException;
    }

    @Override
    public void processing(ExceptionContext exceptionContext) {
        RetryCommand retryCommand = RetryCommand.builder().exceptionContext(exceptionContext).build();
        exceptionContext.getActionsQueue().add(retryCommand);
    }
}
