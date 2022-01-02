package com.otus.solid.first.war.of.tanks.actions.state.movement;

import com.otus.solid.first.war.of.tanks.actions.State;
import com.otus.solid.first.war.of.tanks.actions.state.location.LocationState;
import com.otus.solid.first.war.of.tanks.actions.state.speed.SpeedState;

public interface MovementState extends State {


    LocationState getLocationState();

    void setLocationState(LocationState state);

    SpeedState getSpeedState();

    void setSpeedState(SpeedState state);
}
