package com.otus.solid.first.war.of.tanks.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface StartGame {
    @PostMapping("/start")
    String start(@RequestBody GameUser user);
}
