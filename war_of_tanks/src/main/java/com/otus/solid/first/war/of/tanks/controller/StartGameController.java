package com.otus.solid.first.war.of.tanks.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartGameController implements StartGame {
    @Override
    public String start(GameUser user) {
        return "OK for " + user.userId;
    }
}
