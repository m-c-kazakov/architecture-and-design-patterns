package com.otus.solid.first.war.of.tanks.actions.state.checkers;

import com.otus.solid.first.war.of.tanks.actions.state.speed.SpeedState2D;
import com.otus.solid.first.war.of.tanks.exceptions.IncorrectDataException;

import static java.util.Objects.isNull;

public class SpeedStateCheckerImpl implements SpeedStateChecker{
    @Override
    public boolean isNeedToCheck(SpeedState2D state) {
        return state.getCheckersName().stream().anyMatch("SpeedState2DChecker"::equals);
    }

    @Override
    public void check(SpeedState2D state) {
        if (isNull(state.getX()) || isNull(state.getY())) {
            throw new IncorrectDataException("SpeedState содержит не корректнче данные");
        }
    }
}
