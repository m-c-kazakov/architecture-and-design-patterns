package com.otus.client;

import com.otus.authorization.dto.UserDataDto;
import com.otus.solid.first.war.of.tanks.controller.GameUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ClientController {

    private final TankBattleFeign tankBattleFeign;
    private final StartGameFeign startGameFeign;

    @PostMapping("/registerAndSend")
    public void registerAndSend(@RequestBody ClientDto clientDto) {
        Set<String> membersId = clientDto.getMembersId();
        log.info(">> registerAndSend");
        final String battleId = tankBattleFeign.receiveMembers(membersId);
        membersId.stream().map(userId -> UserDataDto.builder().battleId(battleId).userId(userId).build()).forEach(userDataDto -> {
            String jwt = tankBattleFeign.getJwtByUserIdAndBattleId(userDataDto);
            startGameFeign.start(new GameUser(userDataDto.getUserId(), null), jwt);
        });
        log.info("<< registerAndSend");
    }

    @Data
    @NoArgsConstructor
    private static class ClientDto {
        Set<String> membersId;
    }
}
