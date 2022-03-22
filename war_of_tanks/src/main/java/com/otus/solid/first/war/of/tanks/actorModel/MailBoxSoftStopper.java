package com.otus.solid.first.war.of.tanks.actorModel;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.iocResolvers.IoC;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MailBoxSoftStopper implements Action {

    String scopeId;

    /**
     * Написать команду, которая останавливает цикл выполнения команд из пункта 1,
     * только после того, как все команды завершат свою работу (soft stop).
     */
    @Override
    public void execute() {
        Action action = () -> ((AtomicBoolean) IoC.resolve(Map.of("dependencyName", "MailBoxIsWorking", "scopeId", scopeId))).set(false);
        final ConcurrentLinkedQueue<Action> actionQueue = IoC.resolve(Map.of("dependencyName", "MailBox", "scopeId", scopeId));
        if (actionQueue.size() == 0) {
            Action resolve = IoC.resolve(Map.of("dependencyName", "MailBoxHardStopper", "scopeId", scopeId));
            resolve.execute();
        } else {
            IoC.resolve(Map.of("dependencyName", "MailBoxAdder", "action", action, "scopeId", scopeId));
        }
    }
}
