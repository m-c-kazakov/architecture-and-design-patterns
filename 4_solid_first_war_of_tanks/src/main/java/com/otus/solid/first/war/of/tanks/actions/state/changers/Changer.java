package com.otus.solid.first.war.of.tanks.actions.state.changers;

import com.otus.solid.first.war.of.tanks.actions.State;

public interface Changer<T extends State> {

    void execute(T state);
}
