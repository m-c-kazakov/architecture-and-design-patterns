package com.otus.solid.first.war.of.tanks.exceptionHandling.excrptionHandlers;

import com.otus.solid.first.war.of.tanks.exceptionHandling.commands.ExceptionLogging;
import com.otus.solid.first.war.of.tanks.exceptionHandling.context.ExceptionContext;
import org.springframework.aop.AopInvocationException;

public class AnImpossibleActionExceptionHandler implements ExceptionHandler {
    @Override
    public boolean isNeedToProcess(ExceptionContext exceptionContext) {
        return exceptionContext.getException() instanceof AopInvocationException;
    }

    @Override
    public void processing(ExceptionContext exceptionContext) {
        ExceptionLogging build = ExceptionLogging.builder().exceptionContext(exceptionContext).build();
        build.execute();
    }
}
