package com.otus.authorization.repository;

import com.otus.authorization.entity.Сombatant;

import java.util.Set;

public interface BattleRepository {
    /**
     * Присваивает группе пользователей ID боя
     * @param membersId
     * @return id боя
     */
    String addMembersForNewBattle(Set<String> membersId);

    Сombatant getBattleIdByUserId(String userId);
}
