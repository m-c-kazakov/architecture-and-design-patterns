package com.otus.solid.first.war.of.tanks.actorModel;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.controller.Message;
import com.otus.solid.first.war.of.tanks.iocResolvers.IoC;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Map;


/**
 * Команда InterpretCommand получает всю информацию об операции, которую необходимо выполнить, параметрах и объекте, над которым эта операция будет выполняться.
 * <p>
 * Задача команды InterpretCommand на основе IoC контейнера создать команду для нужного объекта, которая которая соответствует приказу, содержащемуся в сообщении и постановки этой команды в очередь команд игры.
 */
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InterpretCommand implements Action {

    Message message;

    @Override
    public void execute() {

        Action action = IoC.resolve(message.getVarargsForIoC());
        MailBoxAdder mailBoxAdder = IoC.resolve(
                Map.of("dependencyName", "MailBoxAdder",
                        "action", action,
                        "scopeId", message.getScopeId()));
        mailBoxAdder.execute();
    }
}
