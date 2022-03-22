package com.otus.solid.first.war.of.tanks.actions.changers;

import com.otus.solid.first.war.of.tanks.actions.Command;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.FuelCheckerCommand;
import com.otus.solid.first.war.of.tanks.actions.state.movement.MovementState2d;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Макрокоманда
 * Последовательно выполняет команды, по проверке изи изменению состояния MovementState2d или его наследников
 */
public class MacroMoveCommand implements MoveChanger {

    private final List<Command<MovementState2d>> chainOfCommand = new ArrayList<>();

    {
        chainOfCommand.add((Command) new FuelCheckerCommand());
        chainOfCommand.add(new MicroMoveChanger());
        chainOfCommand.add((Command) (new FuelBurnChangerCommand()));
    }

    @Override
    public void execute(MovementState2d state) {

        chainOfCommand.forEach(command -> command.execute(state));

    }
}
