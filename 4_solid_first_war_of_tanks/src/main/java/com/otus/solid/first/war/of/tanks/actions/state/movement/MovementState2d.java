package com.otus.solid.first.war.of.tanks.actions.state.movement;

import com.otus.solid.first.war.of.tanks.actions.State;
import com.otus.solid.first.war.of.tanks.actions.state.location.LocationState2d;
import com.otus.solid.first.war.of.tanks.actions.state.speed.SpeedState2D;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
@AllArgsConstructor
public class MovementState2d implements State {
    @NonNull
    private List<String> checkersName;
    private LocationState2d locationState2d;
    private SpeedState2D speedState;
}
