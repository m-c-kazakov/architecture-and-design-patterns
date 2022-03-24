package com.otus.solid.first.war.of.tanks.exceptionHandling;

import com.otus.solid.first.war.of.tanks.exceptionHandling.context.ExceptionContext;
import com.otus.solid.first.war.of.tanks.exceptionHandling.excrptionHandlers.ExceptionHandler;
import lombok.Setter;

import java.util.List;

@Setter
public class BaseExceptionsHandlerImpl implements BaseExceptionsHandler {

    List<ExceptionHandler> exceptionHandler;

    @Override
    public void processing(ExceptionContext exceptionContext) {
        exceptionHandler.stream().filter(eH -> eH.isNeedToProcess(exceptionContext)).forEach(eH -> eH.processing(exceptionContext));
    }
}
