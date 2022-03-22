package com.otus.solid.first.war.of.tanks.actions.changers;

import com.otus.solid.first.war.of.tanks.actions.Command;
import com.otus.solid.first.war.of.tanks.actions.move.MovementImpl;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.FuelCheckerCommand;
import com.otus.solid.first.war.of.tanks.actions.state.location.LocationState2d;
import com.otus.solid.first.war.of.tanks.actions.state.movement.MovementState2d;
import com.otus.solid.first.war.of.tanks.actions.state.movement.MovementStateWithFuel2d;
import com.otus.solid.first.war.of.tanks.actions.state.speed.SpeedState2D;
import com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions.CommandException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class MacroMoveCommandTest {

    @Test
    void execute$Success() {

        MacroMoveCommand macroMoveCommand = new MacroMoveCommand();

        LocationState2d locationState2d = LocationState2d.builder().checkers(Collections.emptyList()).x(12L).y(5L).build();
        SpeedState2D speedState = new SpeedState2D(Collections.emptyList(), -7L, 3L);
        MovementStateWithFuel2d state = new MovementStateWithFuel2d(List.of(), locationState2d, speedState, 100L);

        MovementImpl movement = new MovementImpl(state);
        movement.setChanger(macroMoveCommand);
        movement.execute();

        LocationState2d locationState = state.getLocationState2d();
        assertEquals(5L, locationState.getX(), "Значение X Не соответствует ожидаемому: 5");
        assertEquals(8L, locationState.getY(), "Значение y Не соответствует ожидаемому: 8");
        assertEquals(90L, state.getFuel(), "Ожидаемое количество топлива: 90");
    }

    @Test
    void execute$Exception() {

        MacroMoveCommand macroMoveCommand = new MacroMoveCommand();

        LocationState2d locationState2d = LocationState2d.builder().checkers(Collections.emptyList()).x(12L).y(5L).build();
        SpeedState2D speedState = new SpeedState2D(Collections.emptyList(), -7L, 3L);
        MovementStateWithFuel2d state = new MovementStateWithFuel2d(List.of(), locationState2d, speedState, 1L);

        MovementImpl movement = new MovementImpl(state);
        movement.setChanger(macroMoveCommand);

        assertThrows(CommandException.class, movement::execute);
    }
}