package com.otus.authorization.controller;

import com.otus.authorization.dto.UserDataDto;
import com.otus.authorization.service.JwtCreator;
import com.otus.authorization.service.BattleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TankBattleController implements TankBattle {

    BattleService battleService;
    JwtCreator jwtCreator;


    @Override
    public String receiveMembers(Set<String> membersId) {

        return battleService.createBattleForUsers(membersId);
    }

    @Override
    public String getJwtByUserIdAndBattleId(UserDataDto userDataDto) {
        return null;
    }
}
