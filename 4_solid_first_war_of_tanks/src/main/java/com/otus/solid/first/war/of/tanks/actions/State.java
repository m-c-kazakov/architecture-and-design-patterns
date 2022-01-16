package com.otus.solid.first.war.of.tanks.actions;

import com.otus.solid.first.war.of.tanks.actions.state.checkers.Checker;
import lombok.NonNull;

import java.util.List;

public interface State<T extends Checker> {
     @NonNull
     void setCheckers(List<T> checkers);

     void check();

}
