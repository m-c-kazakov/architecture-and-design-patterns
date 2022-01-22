package com.otus.solid.first.war.of.tanks.actions.state.turn;

import com.otus.solid.first.war.of.tanks.actions.CheckerOwner;
import com.otus.solid.first.war.of.tanks.actions.State;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.TurnStateChecker;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TurnState2D implements State, CheckerOwner<TurnStateChecker> {

    private List<TurnStateChecker> checkers;
    private String currentDirection;

    @Override
    public void check() {
        checkers.forEach(stateChecker -> stateChecker.check(this));
    }
}
