package com.otus.solid.first.war.of.tanks.actions.state.movement;

import com.otus.solid.first.war.of.tanks.actions.CheckerOwner;
import com.otus.solid.first.war.of.tanks.actions.State;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.MovementStateChecker;
import com.otus.solid.first.war.of.tanks.actions.state.location.LocationState2d;
import com.otus.solid.first.war.of.tanks.actions.state.speed.SpeedState2D;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MovementState2d implements State, CheckerOwner<MovementStateChecker> {

    @Setter
    private List<MovementStateChecker> checkers;
    private LocationState2d locationState2d;
    private SpeedState2D speedState;

    @Override
    public void check() {
        checkers.stream().filter(stateChecker -> stateChecker.isNeedToCheck(this)).forEach(stateChecker -> stateChecker.check(this));
    }
}
