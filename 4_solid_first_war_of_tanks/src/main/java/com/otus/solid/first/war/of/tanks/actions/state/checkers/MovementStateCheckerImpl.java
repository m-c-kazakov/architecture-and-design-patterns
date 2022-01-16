package com.otus.solid.first.war.of.tanks.actions.state.checkers;

import com.otus.solid.first.war.of.tanks.actions.borders.BorderState2D;
import com.otus.solid.first.war.of.tanks.actions.state.location.LocationState2d;
import com.otus.solid.first.war.of.tanks.actions.state.movement.MovementState2d;
import com.otus.solid.first.war.of.tanks.actions.state.speed.SpeedState2D;
import com.otus.solid.first.war.of.tanks.exceptions.ImpossibleActionException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MovementStateCheckerImpl implements MovementStateChecker{

    private final BorderState2D borderState2D;
    List<LocationStateChecker> locationStateCheckers;
    List<SpeedStateChecker> speedStateChecker;

    @Override
    public boolean isNeedToCheck(MovementState2d state) {
        return state.getCheckersName().stream().anyMatch("MovementStateChecker"::equals);
    }

    @Override
    public void check(MovementState2d state) {
        locationStateCheckers.stream().filter(stateChecker -> stateChecker.isNeedToCheck(state.getLocationState2d())).forEach(stateChecker -> stateChecker.check(state.getLocationState2d()));
        speedStateChecker.stream().filter(stateChecker -> stateChecker.isNeedToCheck(state.getSpeedState())).forEach(stateChecker -> stateChecker.check(state.getSpeedState()));

        LocationState2d locationState = state.getLocationState2d();
        SpeedState2D speedState = state.getSpeedState();
        Long x = locationState.getX() + speedState.getX();
        Long y = locationState.getY() + speedState.getY();

        if (!borderState2D.isLocatedIdBorders(x)|| !borderState2D.isLocatedIdBorders(y)) {
            throw new ImpossibleActionException("Не возможно изменить положение в пространстве");
        }
    }
}
