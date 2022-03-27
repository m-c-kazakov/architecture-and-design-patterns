package com.otus.solid.first.war.of.tanks.actorModel;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.FuelCheckerCommand;
import com.otus.solid.first.war.of.tanks.actorModel.state.MailBoxState;
import com.otus.solid.first.war.of.tanks.iocResolvers.IoC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MailBoxHardStopperTest {
    @Mock
    private Action action1;
    @Mock
    private Action action2;

    @Test
    @DisplayName("Написать тест, который проверяет, что после команды hard stop, поток завершается ")
    void execute() {

        Function<Map<String, Object>, AtomicBoolean> initFuncAtomicBoolean = (map) -> new AtomicBoolean(true);
        IoC.resolve(Map.of("dependencyName", "MailBoxIsWorking", "initFunc", initFuncAtomicBoolean, "isSingleton", true, "scopeId", "MailBoxHardStopperTest"));

        Function<Map<String, Object>, ConcurrentLinkedQueue<Action>> initFuncConcurrentLinkedQueue = (map) -> new ConcurrentLinkedQueue();
        IoC.resolve(Map.of("dependencyName", "MailBox", "initFunc", initFuncConcurrentLinkedQueue, "isSingleton", true, "scopeId", "MailBoxHardStopperTest"));

        Function<Map<String, Object>, MailBoxExecutor> initFuncMailBoxExecutor = (map) -> new MailBoxExecutor((String) map.get("scopeId"), new MailBoxState((String) map.get("scopeId")));
        IoC.resolve(Map.of("dependencyName", "MailBoxExecutor", "initFunc", initFuncMailBoxExecutor, "scopeId", "MailBoxHardStopperTest"));

        Function<Map<String, Object>, MailBoxHardStopper> initFuncMailBoxHardStopper = (map) -> new MailBoxHardStopper((String) map.get("scopeId"));
        IoC.resolve(Map.of("dependencyName", "MailBoxHardStopper", "initFunc", initFuncMailBoxHardStopper, "scopeId", "MailBoxHardStopperTest"));

        Function<Map<String, Object>, MailBoxSoftStopper> initFuncMailBoxSoftStopper = (map) -> new MailBoxSoftStopper((String) map.get("scopeId"));
        IoC.resolve(Map.of("dependencyName", "MailBoxSoftStopper", "initFunc", initFuncMailBoxSoftStopper, "scopeId", "MailBoxHardStopperTest"));

        Function<Map<String, Object>, MailBoxAdder> initFuncMailBoxAdder = (map) -> new MailBoxAdder((Action) map.get("action"), (String) map.get("scopeId"));
        IoC.resolve(Map.of("dependencyName", "MailBoxAdder", "initFunc", initFuncMailBoxAdder, "scopeId", "MailBoxHardStopperTest"));


        MailBoxAdder mailBoxAdder1 = new MailBoxAdder(action1, "MailBoxHardStopperTest");
        MailBoxAdder mailBoxAdder2 = new MailBoxAdder(action2, "MailBoxHardStopperTest");
        Action hardStopCommand = IoC.resolve(Map.of("dependencyName", "MailBoxHardStopper", "scopeId", "MailBoxHardStopperTest"));
        MailBoxAdder mailBoxAdderHardStopper = new MailBoxAdder(hardStopCommand, "MailBoxHardStopperTest");

        mailBoxAdder1.execute();
        mailBoxAdderHardStopper.execute();
        mailBoxAdder2.execute();
//IoC.resolve(Map.of("dependencyName", "MailBox"))
        MailBoxStarter mailBoxStarter = new MailBoxStarter("MailBoxHardStopperTest");
        mailBoxStarter.execute();

        verify(action1, timeout(100).times(1)).execute();
        verify(action2, timeout(100).times(0)).execute();
    }
}