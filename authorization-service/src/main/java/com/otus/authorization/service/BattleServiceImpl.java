package com.otus.authorization.service;

import com.otus.authorization.dto.PayLoad;
import com.otus.authorization.dto.UserDataDto;
import com.otus.authorization.exception.AuthError;
import com.otus.authorization.repository.BattleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.Map;
import java.util.Set;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PACKAGE)
public class BattleServiceImpl implements BattleService {

    BattleRepository battleRepository;
    JwtCreator jwtCreator;

    @NonFinal
    @Value("${otus.auth.jwt.lifeTime}")
    Long jwtLifeTime;

    @Override
    public String createBattleForUsers(Set<String> membersId) {
        return battleRepository.addMembersForNewBattle(membersId);
    }

    @Override
    public String getJws(UserDataDto userDataDto) {
        return ofNullable(battleRepository.getBattleIdByUserId(userDataDto.getUserId()))
                .filter(combatant -> combatant.getBattleId().equals(userDataDto.getBattleId()))
                .map(combatant -> PayLoad.builder()
                        .userId(combatant.getMemberId())
                        .expiration(Date.from(Instant.now().plusSeconds(jwtLifeTime)))
                        .optionalParams(Map.of("battleId", combatant.getBattleId()))
                        .build())
                .map(jwtCreator::execute)
                .orElseThrow(AuthError::new);
    }
}
