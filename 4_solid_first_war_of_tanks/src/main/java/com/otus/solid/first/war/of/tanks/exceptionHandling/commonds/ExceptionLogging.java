package com.otus.solid.first.war.of.tanks.exceptionHandling.commonds;

import com.otus.solid.first.war.of.tanks.exceptionHandling.context.ExceptionContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionLogging implements ExceptionCommand{

    @Override
    public void execute(ExceptionContext exceptionContext) {
        log.error("Возникло исключение при попытке выполнить действей игрового объекта: "+exceptionContext.toString());
    }
}
