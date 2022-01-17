package com.otus.solid.first.war.of.tanks.actions.state.checkers;

public interface Checker<T> {

    boolean isNeedToCheck(T state);

    void check(T state);
}
