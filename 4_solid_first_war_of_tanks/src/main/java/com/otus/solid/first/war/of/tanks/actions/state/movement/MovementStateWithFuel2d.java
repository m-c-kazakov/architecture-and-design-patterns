package com.otus.solid.first.war.of.tanks.actions.state.movement;

import com.otus.solid.first.war.of.tanks.actions.state.checkers.MovementStateChecker;
import com.otus.solid.first.war.of.tanks.actions.state.location.LocationState2d;
import com.otus.solid.first.war.of.tanks.actions.state.speed.SpeedState2D;
import lombok.Getter;

import java.util.List;


@Getter
public class MovementStateWithFuel2d extends MovementState2d {

    private final Long fuel;

    public MovementStateWithFuel2d(List<MovementStateChecker> checkers, LocationState2d locationState2d, SpeedState2D speedState, Long fuel) {
        super(checkers, locationState2d, speedState);
        this.fuel = fuel;
    }
}
