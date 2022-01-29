package com.otus.solid.first.war.of.tanks.exceptionHandling.commands;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.exceptionHandling.context.ExceptionContext;
import com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions.AnImpossibleActionException;
import lombok.Builder;
import lombok.Setter;

import java.util.Queue;

/**
 * Команда, которая повторяет Команду, выбросившую исключение
 */
@Setter
@Builder
public class RetryCommand implements ExceptionCommand {

    private ExceptionContext exceptionContext;


    @Override
    public void execute() {
        exceptionContext.getCommand().execute();
//        throw new AnImpossibleActionException(exception, this.getClass().getCanonicalName(), numberOfAttempts + " Попытки выполнить действие завершились ошибкой");
    }
}
