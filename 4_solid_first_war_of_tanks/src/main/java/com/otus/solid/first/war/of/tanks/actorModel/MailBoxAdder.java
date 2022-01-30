package com.otus.solid.first.war.of.tanks.actorModel;

import com.otus.solid.first.war.of.tanks.actions.Action;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

@RequiredArgsConstructor
public class MailBoxAdder implements Action {

    private final Action action;

    // todo task Добавить реализацию после проверки задачи на IoC
    private IoC ioC;

    @Override
    public void execute() {

        final ConcurrentLinkedQueue<Action> actionQueue = ioC.resolve(Map.of("dependencyName", "MailBox"));

        actionQueue.add(action);

    }
}
