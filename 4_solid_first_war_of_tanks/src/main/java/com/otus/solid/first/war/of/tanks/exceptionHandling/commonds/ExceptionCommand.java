package com.otus.solid.first.war.of.tanks.exceptionHandling.commonds;

import com.otus.solid.first.war.of.tanks.exceptionHandling.context.ExceptionContext;

public interface ExceptionCommand {

    void execute(ExceptionContext exceptionContext);
}
