package com.otus.solid.first.war.of.tanks.actions.move;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.actions.state.changers.MoveChanger;

/**
 * Прямолинейное равномерное движение без деформации.
 */
public interface Movement extends Action<MoveChanger> {
}
