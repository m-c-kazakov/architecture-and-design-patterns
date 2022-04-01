package com.otus.solid.first.war.of.tanks.service.senders;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "BattleFeign",
        contextId = "BattleFeignContextId",
        url = "${application.auth.url]"
)
public interface BattleFeign extends BattleResource {
}
