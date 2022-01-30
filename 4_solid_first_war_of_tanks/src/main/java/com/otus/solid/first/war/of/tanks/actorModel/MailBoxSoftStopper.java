package com.otus.solid.first.war.of.tanks.actorModel;

import com.otus.solid.first.war.of.tanks.actions.Action;

import java.util.Map;
import java.util.function.Function;

public class MailBoxSoftStopper implements Action {

    // todo task Добавить реализацию после проверки задачи на IoC
    private IoC ioC;

    /**
     * Написать команду, которая останавливает цикл выполнения команд из пункта 1,
     * только после того, как все команды завершат свою работу (soft stop).
     */
    @Override
    public void execute() {

        Function<Map<String, Object>, Object> initFunc = (map) -> ioC.resolve(Map.of("dependencyName", "MailBoxHardStopper"));
        ioC.resolve(Map.of("dependencyName", "AbsenceOfElementAction", "initFunc", initFunc));
    }
}
