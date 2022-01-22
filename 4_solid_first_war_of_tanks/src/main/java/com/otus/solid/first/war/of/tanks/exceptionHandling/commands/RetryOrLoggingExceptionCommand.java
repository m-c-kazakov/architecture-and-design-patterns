package com.otus.solid.first.war.of.tanks.exceptionHandling.commands;

import com.otus.solid.first.war.of.tanks.exceptionHandling.context.ExceptionContext;
import lombok.Builder;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Реализовать стратегию обработки исключения - повторить два раза, потом записать в лог.
 * Указание: создать новую команду, точно такую же как в пункте 6. Тип этой команды будет показывать, что Команду не удалось выполнить два раза.
 */
@Slf4j
@Setter
@Builder
public class RetryOrLoggingExceptionCommand implements ExceptionCommand {

    private ExceptionContext exceptionContext;

    @Override
    public void execute() {
        for (int i = 0; i < 2; i++) {
            try {
                exceptionContext.getCommand().execute();
                return;
            } catch (Exception ignore) {
            }
        }

        log.error("Возникло исключение при попытке выполнить действей игрового объекта: "+exceptionContext.getException().getMessage());
    }
}
