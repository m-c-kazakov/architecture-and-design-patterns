package com.otus.authorization.controller;

import com.otus.authorization.dto.ClientDto;
import com.otus.authorization.dto.UserDataDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

public interface TankBattle {

    @PostMapping("/receiveMembers")
    String receiveMembers(@RequestBody ClientDto clientDto);

    @PostMapping("/getJwtByUserIdAndBattleId")
    String getJwtByUserIdAndBattleId(@RequestBody UserDataDto userDataDto);
}
