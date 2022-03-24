package com.otus.solid.first.war.of.tanks.exceptionHandling.excrptionHandlers;

import com.otus.solid.first.war.of.tanks.exceptionHandling.context.ExceptionContext;

/**
 * Обработчик конкретных исключений.
 * Решает нужно ли ему обработать это исклюение.
 * Если да то обрабатывает его
 */
public interface ExceptionHandler {

    boolean isNeedToProcess(ExceptionContext exceptionContext);

    void processing(ExceptionContext exceptionContext);

}
