package com.otus.solid.first.war.of.tanks.exceptionHandling.turn;

import com.otus.solid.first.war.of.tanks.actions.Command;
import com.otus.solid.first.war.of.tanks.actions.State;
import com.otus.solid.first.war.of.tanks.actions.changers.TurnChanger;
import com.otus.solid.first.war.of.tanks.actions.state.turn.TurnState2D;
import lombok.Builder;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
public class TurnImpl implements Turn {
    private final TurnState2D state;
    private final List<Command<State>> checkers;
    private TurnChanger changer;

    @Override
    public void execute() {
        checkers.forEach(stateChecker -> stateChecker.execute(state));
        changer.execute(state);
    }
}
