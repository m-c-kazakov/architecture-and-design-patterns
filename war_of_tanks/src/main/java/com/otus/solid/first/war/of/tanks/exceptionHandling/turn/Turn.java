package com.otus.solid.first.war.of.tanks.exceptionHandling.turn;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.actions.ChangeOwner;
import com.otus.solid.first.war.of.tanks.actions.changers.TurnChanger;

public interface Turn extends Action, ChangeOwner<TurnChanger> {
}
