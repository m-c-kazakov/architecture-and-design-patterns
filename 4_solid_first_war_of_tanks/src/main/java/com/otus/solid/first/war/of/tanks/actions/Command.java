package com.otus.solid.first.war.of.tanks.actions;

public interface Command<T> {

    void execute(T state);
}
