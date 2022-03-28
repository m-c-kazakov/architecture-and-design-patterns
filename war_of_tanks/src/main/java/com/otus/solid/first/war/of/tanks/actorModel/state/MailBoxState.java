package com.otus.solid.first.war.of.tanks.actorModel.state;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.actions.move.MoveToCommand;
import com.otus.solid.first.war.of.tanks.actions.move.RunCommand;
import com.otus.solid.first.war.of.tanks.actorModel.MailBoxHardStopper;
import com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions.AnImpossibleActionException;
import com.otus.solid.first.war.of.tanks.iocResolvers.IoC;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.util.Optional.ofNullable;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MailBoxState implements MailBoxExecutorState {

    final String scopeId;
    MailBoxExecutorState state;

    public MailBoxState(String scopeId) {
        this.state = new DefaultState();
        this.scopeId = scopeId;
    }

    @Override
    public void handle() {
        if (Objects.nonNull(state)) {
            state.handle();
        } else {
            throw new AnImpossibleActionException("MailBoxState", "Отсутствует внутреннее состояние для выполнения действия.");
        }
    }

    /**
     * Default
     * В этом состоянии очередная команда извлекается из очереди и выполняется.
     * По умолчанию возвращается ссылка на этот же экземпляр состояния.
     * Обработка команды HardStop приводит к тому, что будет возвращена "нулевая ссылка" на следующее состояние, что соответствует завершению работы потока.
     * Обработка команды MoveToCommand приводит к тому, что будет возвращена ссылка на состояние MoveTo
     */
    class DefaultState implements MailBoxExecutorState {

        @Override
        public void handle() {
            // В этом состоянии очередная команда извлекается из очереди и выполняется.
            final ConcurrentLinkedQueue<Action> actionQueue = IoC.resolve(Map.of("dependencyName", "MailBox", "scopeId", scopeId));
            ofNullable(actionQueue.poll()).ifPresentOrElse(action -> {
                if (action instanceof MailBoxHardStopper) {
                    // Обработка команды HardStop приводит к тому, что будет возвращена "нулевая ссылка" на следующее состояние, что соответствует завершению работы потока.
                    state = null;
                } else if (action instanceof MoveToCommand) {
                    // Обработка команды MoveToCommand приводит к тому, что будет возвращена ссылка на состояние MoveTo
                    state = new MoveToState();
                } else {
                    // По умолчанию возвращается ссылка на этот же экземпляр состояния.
                    state = this;
                }
                action.execute();
            }, () -> {
                Action action = IoC.resolve(Map.of("dependencyName", "MailBoxSoftStopper", "scopeId", scopeId));
                action.execute();
            });

        }
    }

    /**
     * состояние, в котором команды извлекаются из очереди и перенаправляются в другую очередь.
     * Обработка команды HardStop приводит к тому, что будет возвращена "нулевая ссылка" на следующее состояние, что соответствует завершению работы потока.
     * Обработка команды RunCommand приводит к тому, что будет возвращена ссылка на "обычное" состояние.
     */
    class MoveToState implements MailBoxExecutorState {

        @Override
        public void handle() {
            // состояние, в котором команды извлекаются из очереди и перенаправляются в другую очередь.
            final ConcurrentLinkedQueue<Action> actionQueue = IoC.resolve(Map.of("dependencyName", "MailBox", "scopeId", scopeId));
            ofNullable(actionQueue.poll()).ifPresentOrElse(action -> {
                if (action instanceof MailBoxHardStopper) {
                    // Обработка команды HardStop приводит к тому, что будет возвращена "нулевая ссылка" на следующее состояние, что соответствует завершению работы потока.
                    state = null;
                    action.execute();
                } else if (action instanceof RunCommand) {
                    // Обработка команды RunCommand приводит к тому, что будет возвращена ссылка на "обычное" состояние.
                    state = new DefaultState();
                    action.execute();
                } else {
                    // состояние, в котором команды извлекаются из очереди и перенаправляются в другую очередь.
                    Action mailBoxRedirector = IoC.resolve(Map.of("dependencyName", "MailBoxRedirector", "redirect", action));
                    mailBoxRedirector.execute();
                }
            }, () -> {
                Action action = IoC.resolve(Map.of("dependencyName", "MailBoxSoftStopper", "scopeId", scopeId));
                action.execute();
            });
        }
    }
}
