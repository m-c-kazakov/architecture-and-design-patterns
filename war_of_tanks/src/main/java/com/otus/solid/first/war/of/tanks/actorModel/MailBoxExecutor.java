package com.otus.solid.first.war.of.tanks.actorModel;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.actorModel.state.MailBoxState;
import com.otus.solid.first.war.of.tanks.iocResolvers.IoC;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MailBoxExecutor implements Action {

    String scopeId;
    MailBoxState mailBoxState;

    /**
     * В цикле получает из потокобезопасной очереди команду  запускает ее.
     * Поток читает очередную команду из очереди и выполняет ее.
     * Если выполнение команды прерывается выброшенным исключением, то поток должен отловить его и продолжить работу.
     * Выброс исключения из команды не должен прерывать выполнение потока.
     * Если сообщений нет в очереди, то поток засыпает до тех пор, пока очередь пуста.
     */
    @Override
    public void execute() {
        while (((AtomicBoolean) IoC.resolve(Map.of("dependencyName", "MailBoxIsWorking", "scopeId", scopeId))).get()) {
            try {
                mailBoxState.handle();
            } catch (Exception exception) {
                log.error("Ошибка при выполнении команды.", exception);
            }
        }
    }
}
