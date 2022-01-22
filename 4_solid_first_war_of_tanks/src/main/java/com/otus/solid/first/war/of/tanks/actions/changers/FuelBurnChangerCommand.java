package com.otus.solid.first.war.of.tanks.actions.changers;

import com.otus.solid.first.war.of.tanks.actions.state.movement.MovementStateWithFuel2d;
import com.otus.solid.first.war.of.tanks.actions.state.speed.SpeedState2D;

/**
 * BurnFuelCommand уменьшает количество топлива на скорость расхода топлива.
 */
public class FuelBurnChangerCommand implements Changer<MovementStateWithFuel2d> {

    @Override
    public void execute(MovementStateWithFuel2d state) {
        SpeedState2D speedState = state.getSpeedState();
        long resultFuel = state.getFuel() - Math.abs(speedState.getX()) - Math.abs(speedState.getY());
        state.setFuel(resultFuel);
    }
}
