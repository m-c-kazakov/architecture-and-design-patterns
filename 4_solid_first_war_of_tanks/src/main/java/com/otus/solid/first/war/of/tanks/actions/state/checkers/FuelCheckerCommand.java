package com.otus.solid.first.war.of.tanks.actions.state.checkers;

import com.otus.solid.first.war.of.tanks.actions.Command;
import com.otus.solid.first.war.of.tanks.actions.state.movement.MovementStateWithFuel2d;
import com.otus.solid.first.war.of.tanks.actions.state.speed.SpeedState2D;
import com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions.CommandException;

/**
 * CheckFuelCommand проверяет, что топлива достаточно, если нет, то выбрасывает исключение CommandException.
 */
public class FuelCheckerCommand  implements Command<MovementStateWithFuel2d> {

    @Override
    public void execute(MovementStateWithFuel2d state) {
        SpeedState2D speedState = state.getSpeedState();
        long resultFuel = state.getFuel() - Math.abs(speedState.getX()) - Math.abs(speedState.getY());
        if (resultFuel < 0) {
            throw new CommandException("FuelCheckerCommand", "Недостаточно топлива для выполнения джижения");
        }
    }
}
