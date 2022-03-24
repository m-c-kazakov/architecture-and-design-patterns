package com.otus.solid.first.war.of.tanks.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Endpoint, который принимает входящее сообщение и конвертирует в команду InterpretCommand
 */
public interface MessageController {

    @PostMapping("/executeMessage")
    void executeMessage(@RequestBody Message message);
}
