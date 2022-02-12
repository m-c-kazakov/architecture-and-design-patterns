package com.otus.solid.first.war.of.tanks.actorModel;

import com.otus.solid.first.war.of.tanks.actions.Action;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

@RequiredArgsConstructor
public class MailBoxSoftStopper implements Action {

    // todo task Добавить реализацию после проверки задачи на IoC
    private final IoC ioC;

    /**
     * Написать команду, которая останавливает цикл выполнения команд из пункта 1,
     * только после того, как все команды завершат свою работу (soft stop).
     */
    @Override
    public void execute() {

        Action action = () -> ((AtomicBoolean) ioC.resolve(Map.of("dependencyName", "MailBoxIsWorking"))).set(false);
        Function<Map<String, Object>, Action> mapActionFunction = (map) -> action;
        ioC.resolve(Map.of("dependencyName", "AbsenceOfElementAction", "initFunc", mapActionFunction));
    }
}
