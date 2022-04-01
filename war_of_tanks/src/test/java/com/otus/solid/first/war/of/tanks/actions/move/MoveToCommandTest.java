package com.otus.solid.first.war.of.tanks.actions.move;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.actorModel.*;
import com.otus.solid.first.war.of.tanks.actorModel.state.MailBoxState;
import com.otus.solid.first.war.of.tanks.iocResolvers.IoC;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MoveToCommandTest {

    @Mock
    private MoveToCommand moveToCommand;
    @Mock
    private Action action1;
    @Mock
    private Action action2;

    @Test
    @DisplayName("Написать тест, который проверяет, что после команды MoveToCommand, поток переходит на обработку Команд с помощью состояния MoveTo")
    void execute() {

        Function<Map<String, Object>, AtomicBoolean> initFuncAtomicBoolean = (map) -> new AtomicBoolean(true);
        IoC.resolve(Map.of("dependencyName", "MailBoxIsWorking", "initFunc", initFuncAtomicBoolean, "isSingleton", true, "scopeId", "MoveToCommandTest"));

        Function<Map<String, Object>, ConcurrentLinkedQueue<Action>> initFuncConcurrentLinkedQueue = (map) -> new ConcurrentLinkedQueue();
        IoC.resolve(Map.of("dependencyName", "MailBox", "initFunc", initFuncConcurrentLinkedQueue, "isSingleton", true, "scopeId", "MoveToCommandTest"));

        Function<Map<String, Object>, ConcurrentLinkedQueue<Action>> initFuncConcurrentLinkedQueueDefaultScope = (map) -> new ConcurrentLinkedQueue();
        IoC.resolve(Map.of("dependencyName", "MailBox", "initFunc", initFuncConcurrentLinkedQueueDefaultScope, "isSingleton", true));

        Function<Map<String, Object>, MailBoxState> initFuncMailBoxState = (map) -> new MailBoxState((String) map.get("scopeId"));
        IoC.resolve(Map.of("dependencyName", "MailBoxState", "initFunc", initFuncMailBoxState, "scopeId", "MoveToCommandTest", "isSingleton", true));

        Function<Map<String, Object>, MailBoxExecutor> initFuncMailBoxExecutor = (map) -> new MailBoxExecutor((String) map.get("scopeId"), IoC.resolve(Map.of("dependencyName", "MailBoxState", "scopeId", map.get("scopeId"))));
        IoC.resolve(Map.of("dependencyName", "MailBoxExecutor", "initFunc", initFuncMailBoxExecutor, "scopeId", "MoveToCommandTest", "isSingleton", true));

        Function<Map<String, Object>, MailBoxHardStopper> initFuncMailBoxHardStopper = (map) -> new MailBoxHardStopper((String) map.get("scopeId"));
        IoC.resolve(Map.of("dependencyName", "MailBoxHardStopper", "initFunc", initFuncMailBoxHardStopper, "scopeId", "MoveToCommandTest"));

        Function<Map<String, Object>, MailBoxSoftStopper> initFuncMailBoxSoftStopper = (map) -> new MailBoxSoftStopper((String) map.get("scopeId"));
        IoC.resolve(Map.of("dependencyName", "MailBoxSoftStopper", "initFunc", initFuncMailBoxSoftStopper, "scopeId", "MoveToCommandTest"));

        Function<Map<String, Object>, MailBoxAdder> initFuncMailBoxAdder = (map) -> new MailBoxAdder((Action) map.get("action"), (String) map.get("scopeId"));
        IoC.resolve(Map.of("dependencyName", "MailBoxAdder", "initFunc", initFuncMailBoxAdder, "scopeId", "MoveToCommandTest"));


        MailBoxAdder mailBoxAdder1 = new MailBoxAdder(moveToCommand, "MoveToCommandTest");
        MailBoxAdder mailBoxAdder3 = new MailBoxAdder(action1, "MoveToCommandTest");
        MailBoxAdder mailBoxAdder2 = new MailBoxAdder(action2, "MoveToCommandTest");


        mailBoxAdder1.execute();
        mailBoxAdder3.execute();
        mailBoxAdder2.execute();

        MailBoxStarter mailBoxStarter = new MailBoxStarter("MoveToCommandTest");
        mailBoxStarter.execute();


        verify(moveToCommand, timeout(100).times(1)).execute();
        verify(action2, timeout(100).times(0)).execute();
        MailBoxState mailBoxState = IoC.resolve(Map.of("dependencyName", "MailBoxState", "scopeId", "MoveToCommandTest"));
        assertEquals("com.otus.solid.first.war.of.tanks.actorModel.state.MailBoxState.MoveToState", mailBoxState.getState().getClass().getCanonicalName());



    }
}