package com.otus.solid.first.war.of.tanks.exceptionHandling.excrptionHandlers;

import com.otus.solid.first.war.of.tanks.exceptionHandling.commands.ExceptionLogging;
import com.otus.solid.first.war.of.tanks.exceptionHandling.commands.RetryCommand;
import com.otus.solid.first.war.of.tanks.exceptionHandling.context.ExceptionContext;


/**
 * При первом выбросе исключения повторить команду, при повторном выбросе исключения записать информацию в лог
 */
public class RetryAndLoggingHandler implements ExceptionHandler {
    @Override
    public boolean isNeedToProcess(ExceptionContext exceptionContext) {
        return false;
    }

    @Override
    public void processing(ExceptionContext exceptionContext) {
        RetryCommand retryCommand = RetryCommand.builder().exceptionContext(exceptionContext).build();
        ExceptionLogging exceptionLogging = ExceptionLogging.builder().exceptionContext(exceptionContext).build();

        try {
            retryCommand.execute();
        } catch (Exception exception) {
            exceptionLogging.execute();
        }

    }
}
