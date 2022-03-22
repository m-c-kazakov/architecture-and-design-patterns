package com.otus.solid.first.war.of.tanks.actions;

/**
 * Действие над внутренним состоянием(State) объекта
 */
@FunctionalInterface
public interface Action {

    void execute();
}
