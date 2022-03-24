package com.otus.solid.first.war.of.tanks.actions;

/**
 * Действие направленное на изменение состояния
 */
public interface ChangeOwner<T extends Command> {

    void setChanger(T changer);
}
