package com.otus.solid.first.war.of.tanks.controller;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.iocResolvers.IoC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class MessageControllerImpl implements MessageController {

    @Override
    public void executeMessage(Message message) {
        log.info(">> executeMessage: Получен запрос на выполнение команды");
        Action action = IoC.resolve(Map.of(
                "dependencyName", "InterpretCommand",
                "scopeId", message.getScopeId(),
                "message", message));
        action.execute();
    }
}
