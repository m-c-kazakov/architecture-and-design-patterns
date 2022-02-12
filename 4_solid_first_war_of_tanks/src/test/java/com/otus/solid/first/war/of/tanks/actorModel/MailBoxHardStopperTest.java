package com.otus.solid.first.war.of.tanks.actorModel;

import com.otus.solid.first.war.of.tanks.actions.Action;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MailBoxHardStopperTest {
    // todo task Добавить реализацию после проверки задачи на IoC
    private IoC ioC = new IoC() {
        Map<String, Object> map = new HashMap<>() {{
            put("MailBoxIsWorking", new AtomicBoolean(true));
            ConcurrentLinkedQueue<Action> actionQueue = new ConcurrentLinkedQueue<>();
            put("MailBox", actionQueue);
            put("MailBoxExecutor", new MailBoxExecutor());
        }};

        @Override
        public <T> T resolve(Map<String, Object> varargs) {
            return (T) map.get((String) varargs.get("dependencyName"));
        }

        @Override
        public Map<String, Object> getMap() {
            return map;
        }
    };
    @Mock
    private MailBoxExecutor mailBoxExecutor;
    @Mock
    private Action action1;
    @Mock
    private Action action2;

    @Test
    @DisplayName("Написать тест, который проверяет, что после команды hard stop, поток завершается ")
    void execute() {
        MailBoxExecutor mailBoxExecutor = ioC.resolve(Map.of("dependencyName", "MailBoxExecutor"));
        mailBoxExecutor.setIoC(ioC);


        MailBoxAdder mailBoxAdder1 = new MailBoxAdder(action1, ioC);
        MailBoxAdder mailBoxAdder2 = new MailBoxAdder(action2, ioC);
        MailBoxHardStopper mailBoxHardStopper = new MailBoxHardStopper(ioC);
        MailBoxAdder mailBoxAdderHardStopper = new MailBoxAdder(mailBoxHardStopper, ioC);

        mailBoxAdder1.execute();
        mailBoxAdderHardStopper.execute();
        mailBoxAdder2.execute();

        MailBoxStarter mailBoxStarter = new MailBoxStarter(ioC);
        mailBoxStarter.execute();

        verify(action1, timeout(100).times(1)).execute();
        verify(action2, timeout(100).times(0)).execute();
    }
}