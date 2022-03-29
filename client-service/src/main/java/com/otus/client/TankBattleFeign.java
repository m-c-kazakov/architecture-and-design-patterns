package com.otus.client;

import com.otus.authorization.controller.TankBattle;
import com.otus.authorization.dto.UserDataDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@FeignClient(
        name = "TankBattleFeign",
        contextId = "TankBattleFeign",
        url = "${application.auth.url}"
)
public interface TankBattleFeign {

    @PostMapping("/receiveMembers")
    String receiveMembers(@RequestBody Set<String> membersId);

    @PostMapping("/getJwtByUserIdAndBattleId")
    String getJwtByUserIdAndBattleId(@RequestBody UserDataDto userDataDto);
}
