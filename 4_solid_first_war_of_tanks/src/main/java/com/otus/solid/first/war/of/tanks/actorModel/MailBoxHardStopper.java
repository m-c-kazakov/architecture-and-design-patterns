package com.otus.solid.first.war.of.tanks.actorModel;

import com.otus.solid.first.war.of.tanks.actions.Action;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class MailBoxHardStopper implements Action {

    // todo task Добавить реализацию после проверки задачи на IoC
    private IoC ioC;

    /**
     * Написать команду, которая останавливает цикл выполнения команд из пункта 1, не дожидаясь их полного завершения (hard stop).
     */
    @Override
    public void execute() {
        ((AtomicBoolean) ioC.resolve(Map.of("dependencyName", "MailBoxIsWorking"))).set(false);
    }
}