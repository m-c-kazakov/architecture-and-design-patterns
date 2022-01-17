package com.otus.solid.first.war.of.tanks.exceptionHandling.turn;

import com.otus.solid.first.war.of.tanks.actions.State;
import com.otus.solid.first.war.of.tanks.actions.changers.TurnChanger;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.Checker;
import com.otus.solid.first.war.of.tanks.actions.state.turn.TurnState2D;
import lombok.Builder;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
public class TurnImpl implements Turn {
    private final TurnState2D state;
    private final List<Checker<State>> checkers;
    private TurnChanger changer;

    @Override
    public void execute() {
        checkers.stream().filter(stateChecker -> stateChecker.isNeedToCheck(state)).forEach(stateChecker -> stateChecker.check(state));
        changer.execute(state);
    }
}
