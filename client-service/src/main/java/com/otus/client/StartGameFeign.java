package com.otus.client;

import com.otus.solid.first.war.of.tanks.controller.GameUser;
import com.otus.solid.first.war.of.tanks.controller.StartGame;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "StartGameFeign",
        contextId = "StartGameFeign",
        url = "${application.warOfTanks.url}"
)
public interface StartGameFeign {

    @PostMapping("/start")
    String start(@RequestBody GameUser user, @RequestHeader(name = "TOKEN") String token);
}
