package com.otus.solid.first.war.of.tanks.actorModel;

import com.otus.solid.first.war.of.tanks.actions.Action;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class MailBoxExecutor implements Action {
    // todo task Добавить реализацию после проверки задачи на IoC
    @Setter
    private IoC ioC;


    /**
     * В цикле получает из потокобезопасной очереди команду  запускает ее.
     * Поток читает очередную команду из очереди и выполняет ее.
     * Если выполнение команды прерывается выброшенным исключением, то поток должен отловить его и продолжить работу.
     * Выброс исключения из команды не должен прерывать выполнение потока.
     * Если сообщений нет в очереди, то поток засыпает до тех пор, пока очередь пуста.
     */
    @Override
    public void execute() {
        final ConcurrentLinkedQueue<Action> actionQueue = ioC.resolve(Map.of("dependencyName", "MailBox"));
        while (((AtomicBoolean) ioC.resolve(Map.of("dependencyName", "MailBoxIsWorking"))).get()) {
            try {
                if (actionQueue.size() != 0) {
                    actionQueue.poll().execute();
                } else {
                    Action action = ioC.resolve(Map.of("dependencyName", "AbsenceOfElementAction"));
                    action.execute();
                }
            } catch (Exception exception) {
                log.error("Ошибка при выполнении команды.", exception);
            }
        }
    }
}
