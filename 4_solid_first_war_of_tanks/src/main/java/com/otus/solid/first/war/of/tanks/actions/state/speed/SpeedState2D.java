package com.otus.solid.first.war.of.tanks.actions.state.speed;

import com.otus.solid.first.war.of.tanks.actions.CheckerOwner;
import com.otus.solid.first.war.of.tanks.actions.State;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.SpeedStateChecker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SpeedState2D implements State, CheckerOwner<SpeedStateChecker> {
    private List<SpeedStateChecker> checkers;
    private Long x;
    private Long y;

    @Override
    public void check() {
        checkers.stream().filter(stateChecker -> stateChecker.isNeedToCheck(this)).forEach(stateChecker -> stateChecker.check(this));
    }
}
