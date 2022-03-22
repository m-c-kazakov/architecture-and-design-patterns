package com.otus.solid.first.war.of.tanks.exceptionHandling.commands;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.exceptionHandling.context.ExceptionContext;

public interface ExceptionCommand extends Action {

    void setExceptionContext(ExceptionContext exceptionContext);
}
