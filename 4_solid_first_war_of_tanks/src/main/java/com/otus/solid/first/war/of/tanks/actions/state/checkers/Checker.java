package com.otus.solid.first.war.of.tanks.actions.state.checkers;

import com.otus.solid.first.war.of.tanks.actions.State;

public interface Checker<T extends State> {
    void check(T state);
}
