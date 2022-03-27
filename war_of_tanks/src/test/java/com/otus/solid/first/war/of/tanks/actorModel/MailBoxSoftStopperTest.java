package com.otus.solid.first.war.of.tanks.actorModel;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.actorModel.state.MailBoxState;
import com.otus.solid.first.war.of.tanks.iocResolvers.IoC;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MailBoxSoftStopperTest {
    @Spy
    private AtomicBoolean atomicBoolean;
    @Mock
    private Action action1;
    @Mock
    private Action action2;

    @BeforeEach
    public void init() {
        atomicBoolean = new AtomicBoolean(true);
    }

    @Test
    @SneakyThrows
    @DisplayName("Написать тест, который проверяет, что после команды soft stop, поток завершается только после того, как все задачи закончились")
    void execute() {

        Function<Map<String, Object>, AtomicBoolean> initFuncAtomicBoolean = (map) -> atomicBoolean;
        IoC.resolve(Map.of("dependencyName", "MailBoxIsWorking", "initFunc", initFuncAtomicBoolean, "scopeId", "MailBoxSoftStopperTest"));

        Function<Map<String, Object>, ConcurrentLinkedQueue<Action>> initFuncConcurrentLinkedQueue = (map) -> new ConcurrentLinkedQueue();
        IoC.resolve(Map.of("dependencyName", "MailBox", "initFunc", initFuncConcurrentLinkedQueue, "isSingleton", true, "scopeId", "MailBoxSoftStopperTest"));

        Function<Map<String, Object>, MailBoxExecutor> initFuncMailBoxExecutor = (map) -> new MailBoxExecutor((String) map.get("scopeId"), new MailBoxState((String) map.get("scopeId")));
        IoC.resolve(Map.of("dependencyName", "MailBoxExecutor", "initFunc", initFuncMailBoxExecutor, "scopeId", "MailBoxSoftStopperTest"));

        Function<Map<String, Object>, MailBoxHardStopper> initFuncMailBoxHardStopper = (map) -> new MailBoxHardStopper((String) map.get("scopeId"));
        IoC.resolve(Map.of("dependencyName", "MailBoxHardStopper", "initFunc", initFuncMailBoxHardStopper, "scopeId", "MailBoxSoftStopperTest"));

        Function<Map<String, Object>, MailBoxSoftStopper> initFuncMailBoxSoftStopper = (map) -> new MailBoxSoftStopper((String) map.get("scopeId"));
        IoC.resolve(Map.of("dependencyName", "MailBoxSoftStopper", "initFunc", initFuncMailBoxSoftStopper, "scopeId", "MailBoxSoftStopperTest"));

        Function<Map<String, Object>, MailBoxAdder> initFuncMailBoxAdder = (map) -> new MailBoxAdder((Action) map.get("action"), (String) map.get("scopeId"));
        IoC.resolve(Map.of("dependencyName", "MailBoxAdder", "initFunc", initFuncMailBoxAdder, "scopeId", "MailBoxSoftStopperTest"));

        MailBoxAdder mailBoxAdder1 = new MailBoxAdder(action1, "MailBoxSoftStopperTest");
        MailBoxAdder mailBoxAdder2 = new MailBoxAdder(action2, "MailBoxSoftStopperTest");
        Action softStopCommand = IoC.resolve(Map.of("dependencyName", "MailBoxSoftStopper", "scopeId", "MailBoxSoftStopperTest"));
        MailBoxAdder mailBoxAdder3 = new MailBoxAdder(softStopCommand, "MailBoxSoftStopperTest");

        mailBoxAdder1.execute();
        mailBoxAdder3.execute();
        mailBoxAdder2.execute();

        MailBoxStarter mailBoxStarter = new MailBoxStarter("MailBoxSoftStopperTest");
        mailBoxStarter.execute();

        verify(action1, timeout(2000).times(1)).execute();
        ConcurrentLinkedQueue<Action> actionQueue = IoC.resolve(Map.of("dependencyName", "MailBox", "scopeId", "MailBoxSoftStopperTest"));
        Thread.sleep(1000);
        assertEquals(0, actionQueue.size());
    }
}