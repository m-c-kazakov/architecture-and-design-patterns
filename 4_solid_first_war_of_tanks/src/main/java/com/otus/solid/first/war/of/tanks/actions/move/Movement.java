package com.otus.solid.first.war.of.tanks.actions.move;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.actions.ChangeOwner;
import com.otus.solid.first.war.of.tanks.actions.changers.MoveChanger;

/**
 * Прямолинейное равномерное движение без деформации.
 */
public interface Movement extends Action, ChangeOwner<MoveChanger> {
}
