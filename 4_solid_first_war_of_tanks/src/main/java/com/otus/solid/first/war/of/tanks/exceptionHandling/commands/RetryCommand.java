package com.otus.solid.first.war.of.tanks.exceptionHandling.commands;

import com.otus.solid.first.war.of.tanks.exceptionHandling.context.ExceptionContext;
import lombok.Builder;
import lombok.Setter;

/**
 * Команда, которыа пробует выполнить команду завершившуюся ошибкой несколько раз
 * В противном случае пишет информацию об ошибке в лог
 */
@Setter
@Builder
public class RetryCommand implements ExceptionCommand {

    private int numberOfAttempts;
    private ExceptionContext exceptionContext;

    @Override
    public void execute() {
        for (int i = 0; i < numberOfAttempts; i++) {
            try {
                exceptionContext.getCommand().execute();
                break;
            } catch (Exception ignored) {
            }
        }

        ExceptionLogging exceptionLogging = ExceptionLogging.builder().exceptionContext(exceptionContext).build();
        exceptionLogging.execute();
    }
}
