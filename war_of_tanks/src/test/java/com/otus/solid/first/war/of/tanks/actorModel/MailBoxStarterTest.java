package com.otus.solid.first.war.of.tanks.actorModel;

import com.otus.solid.first.war.of.tanks.actions.Action;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailBoxStarterTest {
    @Mock
    private MailBoxExecutor mailBoxExecutor;

    @Test
    @DisplayName("Написать тест, который проверяет, что после команды старт поток запущен")
    void execute$1() {
        Function<Map<String, Object>, AtomicBoolean> initFuncAtomicBoolean = (map) -> new AtomicBoolean(true);
        IoC.resolve(Map.of("dependencyName", "MailBoxIsWorking", "initFunc", initFuncAtomicBoolean, "isSingleton", true, "scopeId", "MailBoxStarterTest"));

        Function<Map<String, Object>, ConcurrentLinkedQueue<Action>> initFuncConcurrentLinkedQueue = (map) -> new ConcurrentLinkedQueue();
        IoC.resolve(Map.of("dependencyName", "MailBox", "initFunc", initFuncConcurrentLinkedQueue, "isSingleton", true, "scopeId", "MailBoxStarterTest"));

        Function<Map<String, Object>, MailBoxExecutor> initFuncMailBoxExecutor = (map) -> mailBoxExecutor;
        IoC.resolve(Map.of("dependencyName", "MailBoxExecutor", "initFunc", initFuncMailBoxExecutor, "scopeId", "MailBoxStarterTest"));

        Function<Map<String, Object>, MailBoxHardStopper> initFuncMailBoxHardStopper = (map) -> new MailBoxHardStopper((String) map.get("scopeId"));
        IoC.resolve(Map.of("dependencyName", "MailBoxHardStopper", "initFunc", initFuncMailBoxHardStopper, "scopeId", "MailBoxStarterTest"));

        Function<Map<String, Object>, MailBoxSoftStopper> initFuncMailBoxSoftStopper = (map) -> new MailBoxSoftStopper((String) map.get("scopeId"));
        IoC.resolve(Map.of("dependencyName", "MailBoxSoftStopper", "initFunc", initFuncMailBoxSoftStopper, "scopeId", "MailBoxStarterTest"));

        Function<Map<String, Object>, MailBoxAdder> initFuncMailBoxAdder = (map) -> new MailBoxAdder((Action) map.get("action"), (String) map.get("scopeId"));
        IoC.resolve(Map.of("dependencyName", "MailBoxAdder", "initFunc", initFuncMailBoxAdder, "scopeId", "MailBoxStarterTest"));

        MailBoxStarter mailBoxStarter = new MailBoxStarter("MailBoxStarterTest");
        mailBoxStarter.execute();
        verify(mailBoxExecutor, timeout(100).times(1)).execute();
    }


}