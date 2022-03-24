package com.otus.authorization.controller;

import com.otus.authorization.dto.ClientDto;
import com.otus.authorization.dto.UserDataDto;
import com.otus.authorization.service.BattleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TankBattleController implements TankBattle {

    BattleService battleService;

    @Override
    public String receiveMembers(ClientDto clientDto) {
        Set<String> membersId = clientDto.getMembersId();
        return battleService.createBattleForUsers(membersId);
    }

    @Override
    public String getJwtByUserIdAndBattleId(UserDataDto userDataDto) {
        return battleService.getJws(userDataDto);
    }
}
