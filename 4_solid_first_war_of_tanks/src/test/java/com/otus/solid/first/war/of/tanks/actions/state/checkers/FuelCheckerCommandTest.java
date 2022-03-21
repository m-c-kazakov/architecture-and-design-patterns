package com.otus.solid.first.war.of.tanks.actions.state.checkers;

import com.otus.solid.first.war.of.tanks.actions.state.movement.MovementStateWithFuel2d;
import com.otus.solid.first.war.of.tanks.actions.state.speed.SpeedState2D;
import com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions.CommandException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FuelCheckerCommandTest {

    @Test
    void check$Success() {

        MovementStateWithFuel2d state = new MovementStateWithFuel2d(List.of(), null, new SpeedState2D(List.of(), 2L, 2L), 30L);
        FuelCheckerCommand fuelCheckerCommand = new FuelCheckerCommand();

        Assertions.assertThatCode(() -> fuelCheckerCommand.execute(state)).doesNotThrowAnyException();
    }

    @Test
    void check$Failure() {

        MovementStateWithFuel2d state = new MovementStateWithFuel2d(List.of(), null, new SpeedState2D(List.of(), 2L, 2L), 3L);
        FuelCheckerCommand fuelCheckerCommand = new FuelCheckerCommand();

        assertThrows(CommandException.class, () -> fuelCheckerCommand.execute(state));
    }
}