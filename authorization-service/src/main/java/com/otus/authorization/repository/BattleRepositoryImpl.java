package com.otus.authorization.repository;

import com.otus.authorization.entity.Battle;
import com.otus.authorization.entity.Сombatant;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

@Repository
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BattleRepositoryImpl implements BattleRepository {

    Map<String, Battle> battleDataBase = new HashMap<>();
    Map<String, Сombatant> combatantDataBasem = new HashMap<>();


    @Override
    public String addMembersForNewBattle(Set<String> membersId) {
        String battleId = String.valueOf(new Random().nextInt());
        battleDataBase.put(battleId, Battle.builder().battleId(battleId).memberId(membersId).build());
        membersId.forEach(id -> combatantDataBasem.put(id, Сombatant.builder().battleId(battleId).memberId(id).build()));
        return battleId;
    }

    @Override
    public Сombatant getBattleIdByUserId(String userId) {
        return combatantDataBasem.get(userId);
    }
}
