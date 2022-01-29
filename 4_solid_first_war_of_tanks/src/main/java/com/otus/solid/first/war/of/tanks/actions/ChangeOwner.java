package com.otus.solid.first.war.of.tanks.actions;

import com.otus.solid.first.war.of.tanks.actions.changers.Changer;

/**
 * Действие направленное на изменение состояния
 */
public interface ChangeOwner<T extends Changer> {

    void setChanger(T changer);
}
