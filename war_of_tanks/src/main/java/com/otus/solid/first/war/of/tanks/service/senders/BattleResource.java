package com.otus.solid.first.war.of.tanks.service.senders;

import com.otus.solid.first.war.of.tanks.dto.UserDataDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

public interface BattleResource {

    @PostMapping("/receiveMembers")
    String sendMembers(@RequestBody Set<String> membersId);

    @PostMapping("/getJwtByUserIdAndBattleId")
    String getJwtByUserIdAndBattleId(@RequestBody UserDataDto userDataDto);
}
