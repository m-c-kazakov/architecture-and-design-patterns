package com.otus.solid.first.war.of.tanks.actorModel;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.controller.Message;
import com.otus.solid.first.war.of.tanks.iocResolvers.IoC;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InterpretCommandTest {

    @Mock
    Action action;

    @Mock
    MailBoxAdder mailBoxAdder;

    @Test
    void execute() {

        Message message = new Message("InterpretCommandTest", "gameObjectId", "Action", new HashMap<>());
        InterpretCommand interpretCommand = new InterpretCommand(message);

        Function<Map<String, Object>, Action> initFuncAction = (map) -> action;
        IoC.resolve(Map.of("dependencyName", "Action", "initFunc", initFuncAction, "scopeId", "InterpretCommandTest"));

        Function<Map<String, Object>, MailBoxAdder> initFuncMailBoxAdder = (map) -> mailBoxAdder;
        IoC.resolve(Map.of("dependencyName", "MailBoxAdder", "initFunc", initFuncMailBoxAdder, "scopeId", "InterpretCommandTest"));

        Function<Map<String, Object>, ConcurrentLinkedQueue<Action>> initFuncConcurrentLinkedQueue = (map) -> new ConcurrentLinkedQueue();
        IoC.resolve(Map.of("dependencyName", "MailBox", "initFunc", initFuncConcurrentLinkedQueue, "isSingleton", true, "scopeId", "InterpretCommandTest"));

        interpretCommand.execute();

        verify(mailBoxAdder, timeout(1)).execute();

    }
}