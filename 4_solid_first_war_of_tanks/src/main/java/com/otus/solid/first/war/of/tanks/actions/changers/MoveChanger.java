package com.otus.solid.first.war.of.tanks.actions.changers;

import com.otus.solid.first.war.of.tanks.actions.state.location.LocationState2d;
import com.otus.solid.first.war.of.tanks.actions.state.movement.MovementState2d;
import com.otus.solid.first.war.of.tanks.actions.state.speed.SpeedState2D;

/**
 * MoveCommand
 */
public interface MoveChanger extends Changer<MovementState2d> {

}
