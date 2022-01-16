package com.otus.solid.first.war.of.tanks.actions;

import com.otus.solid.first.war.of.tanks.actions.changers.Changer;

/**
 * Действие над внутренним состоянием(State) объекта
 */
public interface Action<T extends Changer> {

    void setChanger(T changer);

    void execute();
}
