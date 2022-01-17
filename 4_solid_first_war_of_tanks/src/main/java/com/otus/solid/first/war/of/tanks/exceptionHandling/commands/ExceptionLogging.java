package com.otus.solid.first.war.of.tanks.exceptionHandling.commands;

import com.otus.solid.first.war.of.tanks.exceptionHandling.context.ExceptionContext;
import lombok.Builder;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Команда, которая должна вывести информацию об ошибке в лог
 */
@Slf4j
@Setter
@Builder
public class ExceptionLogging implements ExceptionCommand{

    private ExceptionContext exceptionContext;

    public void execute() {
        log.error("Возникло исключение при попытке выполнить действей игрового объекта: "+exceptionContext.toString());
    }
}
