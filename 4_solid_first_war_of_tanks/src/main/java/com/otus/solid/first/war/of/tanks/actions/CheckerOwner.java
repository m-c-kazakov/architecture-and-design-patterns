package com.otus.solid.first.war.of.tanks.actions;

import lombok.NonNull;

import java.util.List;

public interface CheckerOwner<T> {

    @NonNull
    void setCheckers(List<T> checkers);

    void check();
}
