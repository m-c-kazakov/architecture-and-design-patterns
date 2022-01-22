package com.otus.solid.first.war.of.tanks.actions.changers;

import com.otus.solid.first.war.of.tanks.actions.state.movement.MovementState2d;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Consumer;

/**
 * Макрокоманда
 * Последовательно выполняет команды, по проверке изи изменению состояния MovementState2d или его наследников
 */
@RequiredArgsConstructor
public class MacroMoveCommand implements MoveChanger {

    private final List<Consumer<MovementState2d>> chainOfCommand;

    @Override
    public void execute(MovementState2d state) {

        chainOfCommand.forEach(consumer -> consumer.accept(state));

    }
}
