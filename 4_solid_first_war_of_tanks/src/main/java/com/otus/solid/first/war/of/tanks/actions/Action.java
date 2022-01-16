package com.otus.solid.first.war.of.tanks.actions;

import com.otus.solid.first.war.of.tanks.actions.state.changers.Changer;

/**
 * Действие над внутренним состоянием(State) объекта
 */
public interface Action<T extends Changer> {

    void execute(T changer);
}
