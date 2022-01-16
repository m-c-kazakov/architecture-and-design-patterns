package com.otus.solid.first.war.of.tanks.actions.turn;

import com.otus.solid.first.war.of.tanks.actions.State;
import com.otus.solid.first.war.of.tanks.actions.state.changers.TurnChanger;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.Checker;
import com.otus.solid.first.war.of.tanks.actions.state.turn.TurnState2D;
import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public class TurnImpl implements Turn {
    private final TurnState2D state;
    private final List<Checker<State>> checkers;

    @Override
    public void execute(TurnChanger changer) {
        checkers.stream().filter(stateChecker -> stateChecker.isNeedToCheck(state)).forEach(stateChecker -> stateChecker.check(state));
        changer.execute(state);
    }
}
