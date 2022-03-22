package com.otus.solid.first.war.of.tanks.controller;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.iocResolvers.IoC;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MessageControllerImpl implements MessageController {

    @Override
    public void executeMessage(Message message) {
        Action action = IoC.resolve(Map.of(
                "dependencyName", "InterpretCommand",
                "scopeId", message.getScopeId(),
                "message", message));
        action.execute();
    }
}
