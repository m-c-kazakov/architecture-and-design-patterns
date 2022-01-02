package com.otus.solid.first.war.of.tanks.actions.move;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.actions.state.movement.MovementState;

/**
 * Прямолинейное равномерное движение без деформации.
 */
public interface Movement extends Action<MovementState> {
}
