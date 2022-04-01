package com.otus.solid.first.war.of.tanks.actorModel;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.iocResolvers.IoC;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MailBoxRedirector implements Action {

    Action redirectAction;

    @Override
    public void execute() {

        final ConcurrentLinkedQueue<Action> actionQueue = IoC.resolve(Map.of("dependencyName", "MailBox"));
        actionQueue.add(redirectAction);

    }
}
