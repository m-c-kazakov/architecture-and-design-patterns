package com.otus.solid.first.war.of.tanks.actions.state.movement;

import com.otus.solid.first.war.of.tanks.actions.state.location.LocationState;
import com.otus.solid.first.war.of.tanks.actions.state.speed.SpeedState;
import lombok.Builder;

@Builder
public class MovementStateImpl implements MovementState{

    private final String checkerName;
    private LocationState locationState;
    private SpeedState speedState;


    @Override
    public String getCheckerName() {
        return checkerName;
    }

    @Override
    public LocationState getLocationState() {
        return locationState;
    }

    @Override
    public void setLocationState(LocationState state) {
        this.locationState = state;
    }

    @Override
    public SpeedState getSpeedState() {
        return speedState;
    }

    @Override
    public void setSpeedState(SpeedState state) {
        this.speedState = state;
    }
}
