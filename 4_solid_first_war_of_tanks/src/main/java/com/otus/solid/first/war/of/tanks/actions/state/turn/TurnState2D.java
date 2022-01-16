package com.otus.solid.first.war.of.tanks.actions.state.turn;

import com.otus.solid.first.war.of.tanks.actions.State;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.Checker;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.TurnStateChecker;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TurnState2D implements State<TurnStateChecker> {

    private List<TurnStateChecker> checkers;
    private String currentDirection;

    @Override
    public void check() {
        checkers.stream().filter(stateChecker -> stateChecker.isNeedToCheck(this)).forEach(stateChecker -> stateChecker.check(this));
    }
}
