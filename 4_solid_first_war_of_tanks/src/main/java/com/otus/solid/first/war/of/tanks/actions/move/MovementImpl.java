package com.otus.solid.first.war.of.tanks.actions.move;

import com.otus.solid.first.war.of.tanks.actions.State;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.Checker;
import com.otus.solid.first.war.of.tanks.actions.state.location.LocationState;
import com.otus.solid.first.war.of.tanks.actions.state.movement.MovementState;
import com.otus.solid.first.war.of.tanks.actions.state.speed.SpeedState;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class MovementImpl implements Movement {

    private final Map<String, Checker<State>> checkers;

    @Override
    public void execute(MovementState state) {
        LocationState locationState = state.getLocationState();
        checkers.get(locationState.getCheckerName()).check(locationState);
        SpeedState speedState = state.getSpeedState();
        checkers.get(speedState.getCheckerName()).check(speedState);

        checkers.get(state.getCheckerName()).check(state);

        Long x = locationState.getX() + speedState.getX();
        locationState.setX(x);

        Long y = locationState.getY() + speedState.getY();
        locationState.setY(y);


    }
}
