package com.otus.solid.first.war.of.tanks.actions.state.checkers;

import com.otus.solid.first.war.of.tanks.actions.state.borders.BorderState2D;
import com.otus.solid.first.war.of.tanks.actions.state.location.LocationState2d;
import com.otus.solid.first.war.of.tanks.actions.state.movement.MovementState2d;
import com.otus.solid.first.war.of.tanks.actions.state.speed.SpeedState2D;
import com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions.ImpossibleActionException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

import static java.util.Objects.nonNull;

@Getter
@AllArgsConstructor
public class MovementStateCheckerImpl implements MovementStateChecker{

    private final BorderState2D borderState2D;

    @Override
    public void check(MovementState2d state) {
        Optional.ofNullable(state.getLocationState2d()).ifPresent(LocationState2d::check);
        Optional.ofNullable(state.getSpeedState()).ifPresent(SpeedState2D::check);

        LocationState2d locationState = state.getLocationState2d();
        SpeedState2D speedState = state.getSpeedState();
        Long x = locationState.getX() + speedState.getX();
        Long y = locationState.getY() + speedState.getY();

        if (!borderState2D.isLocatedIdBorders(x) && !borderState2D.isLocatedInBound(y)) {
            throw new ImpossibleActionException(this.getClass().getCanonicalName(),  "Не возможно изменить положение в пространстве");
        }
    }
}
