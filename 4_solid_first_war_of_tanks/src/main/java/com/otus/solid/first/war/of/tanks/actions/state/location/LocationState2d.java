package com.otus.solid.first.war.of.tanks.actions.state.location;

import com.otus.solid.first.war.of.tanks.actions.CheckerOwner;
import com.otus.solid.first.war.of.tanks.actions.State;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.LocationStateChecker;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class LocationState2d implements State, CheckerOwner<LocationStateChecker> {
    private List<LocationStateChecker> checkers;
    private Long x;
    private Long y;

    @Override
    public void check() {
        checkers.stream().filter(stateChecker -> stateChecker.isNeedToCheck(this)).forEach(stateChecker -> stateChecker.check(this));
    }
}
