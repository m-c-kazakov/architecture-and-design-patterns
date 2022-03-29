package com.otus.authorization.service;

import com.otus.authorization.dto.UserDataDto;

import java.util.Set;

public interface BattleService {

    /**
     * Создает идентификатор танкового боя и присваивает его пользователям
     * @param membersId
     * @return
     */
    String createBattleForUsers(Set<String> membersId);

    String getJws(UserDataDto userDataDto);
}
