package com.otus.solid.first.war.of.tanks.actions.state.checkers;

import com.otus.solid.first.war.of.tanks.actions.Command;
import com.otus.solid.first.war.of.tanks.actions.state.movement.MovementState2d;

/**
 * Определяет набор правил, по которым будет проверятся возможность совершения движения
 */
public interface MovementStateChecker extends Command<MovementState2d> {
}
