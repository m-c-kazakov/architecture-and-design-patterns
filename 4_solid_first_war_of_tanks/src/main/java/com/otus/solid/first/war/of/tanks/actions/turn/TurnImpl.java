package com.otus.solid.first.war.of.tanks.actions.turn;

import com.otus.solid.first.war.of.tanks.actions.State;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.Checker;
import com.otus.solid.first.war.of.tanks.actions.state.turn.TurnState;
import lombok.Builder;

import java.util.Map;

@Builder
public class TurnImpl implements Turn {
    private final Map<String, Checker<State>> checkers;

    @Override
    public void execute(TurnState state) {
        checkers.get(state.getCheckerName()).check(state);
        state.getAllDesiredDirection().forEach(state::putCurrentDirection);
    }
}
