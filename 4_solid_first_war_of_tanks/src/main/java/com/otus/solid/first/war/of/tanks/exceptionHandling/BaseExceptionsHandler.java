package com.otus.solid.first.war.of.tanks.exceptionHandling;

import com.otus.solid.first.war.of.tanks.exceptionHandling.context.ExceptionContext;
import com.otus.solid.first.war.of.tanks.exceptionHandling.excrptionHandlers.ExceptionHandler;

import java.util.List;

/**
 * Обработчик исключений. Перехватывает все исключения и о
 * пределяе какой конкретной обработчик должен его обработать
 */
public interface BaseExceptionsHandler {

    void setExceptionHandler(List<ExceptionHandler> exceptionHandlers);

    void processing(ExceptionContext exceptionContext);
}
