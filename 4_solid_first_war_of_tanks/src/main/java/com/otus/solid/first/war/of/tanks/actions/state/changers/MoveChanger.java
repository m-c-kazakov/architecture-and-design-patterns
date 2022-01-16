package com.otus.solid.first.war.of.tanks.actions.state.changers;

import com.otus.solid.first.war.of.tanks.actions.state.location.LocationState2d;
import com.otus.solid.first.war.of.tanks.actions.state.movement.MovementState2d;
import com.otus.solid.first.war.of.tanks.actions.state.speed.SpeedState2D;

public class MoveChanger implements Changer<MovementState2d>{

    @Override
    public void execute(MovementState2d state) {
        LocationState2d locationState = state.getLocationState2d();
        SpeedState2D speedState = state.getSpeedState();

        locationState.setX(locationState.getX() + speedState.getX());
        locationState.setY(locationState.getY() + speedState.getY());

    }
}
