package com.otus.solid.first.war.of.tanks.actorModel;

import com.otus.solid.first.war.of.tanks.actions.Action;
import lombok.SneakyThrows;
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
    AtomicBoolean atomicBoolean = new AtomicBoolean(true);
    // todo task Добавить реализацию после проверки задачи на IoC
    private IoC ioC = new IoC() {
        Map<String, Object> map = new HashMap<>() {{
            put("MailBoxIsWorking", atomicBoolean);
            ConcurrentLinkedQueue<Action> actionQueue = new ConcurrentLinkedQueue<>();
            put("MailBox", actionQueue);
            put("MailBoxExecutor", new MailBoxExecutor());
        }};

        @Override
        public <T> T resolve(Map<String, Object> varargs) {
            Function<Map<String, Object>, Action> initFunc = (Function<Map<String, Object>, Action>) varargs.get("initFunc");
            if (Objects.nonNull(initFunc)) {
                map.put((String) varargs.get("dependencyName"), initFunc.apply(varargs));
            }
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
    @SneakyThrows
    @DisplayName("Написать тест, который проверяет, что после команды soft stop, поток завершается только после того, как все задачи закончились")
    void execute() {

        MailBoxExecutor mailBoxExecutor = ioC.resolve(Map.of("dependencyName", "MailBoxExecutor"));
        mailBoxExecutor.setIoC(ioC);
        MailBoxHardStopper mailBoxHardStopper = new MailBoxHardStopper(ioC);
        ioC.getMap().put("MailBoxHardStopper", mailBoxHardStopper);


        MailBoxAdder mailBoxAdder1 = new MailBoxAdder(action1, ioC);
        MailBoxAdder mailBoxAdder2 = new MailBoxAdder(action2, ioC);
        MailBoxSoftStopper mailBoxSoftStopper = new MailBoxSoftStopper(ioC);
        MailBoxAdder mailBoxAdderSoftStopper = new MailBoxAdder(mailBoxSoftStopper, ioC);

        mailBoxAdder1.execute();
        mailBoxAdderSoftStopper.execute();
        mailBoxAdder2.execute();

        MailBoxStarter mailBoxStarter = new MailBoxStarter(ioC);
        mailBoxStarter.execute();

        verify(action1, timeout(2000).times(1)).execute();
        ConcurrentLinkedQueue<Action> actionQueue = ioC.resolve(Map.of("dependencyName", "MailBox"));
        Thread.sleep(1000);
        assertEquals(0, actionQueue.size());
    }
}