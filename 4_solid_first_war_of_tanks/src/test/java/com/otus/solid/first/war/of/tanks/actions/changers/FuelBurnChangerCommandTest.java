package com.otus.solid.first.war.of.tanks.actions.changers;

import com.otus.solid.first.war.of.tanks.actions.state.movement.MovementStateWithFuel2d;
import com.otus.solid.first.war.of.tanks.actions.state.speed.SpeedState2D;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FuelBurnChangerCommandTest {

    @Test
    void execute() {

        MovementStateWithFuel2d state = new MovementStateWithFuel2d(List.of(), null, new SpeedState2D(List.of(), 2L, 2L), 5L);
        FuelBurnChangerCommand fuelBurnChangerCommand = new FuelBurnChangerCommand();
        fuelBurnChangerCommand.execute(state);

        assertEquals(1L, (long) state.getFuel());
    }
}