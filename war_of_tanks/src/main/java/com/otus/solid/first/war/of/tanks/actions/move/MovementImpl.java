package com.otus.solid.first.war.of.tanks.actions.move;

import com.otus.solid.first.war.of.tanks.actions.changers.MoveChanger;
import com.otus.solid.first.war.of.tanks.actions.state.movement.MovementState2d;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@RequiredArgsConstructor
public class MovementImpl implements Movement {

    private final MovementState2d state;
    private MoveChanger changer;

    @Override
    public void execute() {
        state.check();
        changer.execute(state);
    }
}
