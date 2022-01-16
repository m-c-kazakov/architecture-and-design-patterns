package com.otus.solid.first.war.of.tanks.actions.move;

import com.otus.solid.first.war.of.tanks.actions.State;
import com.otus.solid.first.war.of.tanks.actions.state.changers.MoveChanger;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.Checker;
import com.otus.solid.first.war.of.tanks.actions.state.movement.MovementState2d;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MovementImpl implements Movement {

    private final MovementState2d state;
    private final List<Checker<State>> checkers;

    @Override
    public void execute(MoveChanger moveChanger) {
        checkers.stream().filter(stateChecker -> stateChecker.isNeedToCheck(state)).forEach(stateChecker -> stateChecker.check(state));
        moveChanger.execute(state);
    }
}
