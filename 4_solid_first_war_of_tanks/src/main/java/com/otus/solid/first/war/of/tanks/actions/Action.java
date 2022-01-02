package com.otus.solid.first.war.of.tanks.actions;

public interface Action<T extends State> {
    void execute(T state);
}
