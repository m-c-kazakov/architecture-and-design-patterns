package com.otus.solid.first.war.of.tanks.actions.state.checkers;

import com.otus.solid.first.war.of.tanks.actions.state.speed.SpeedState2D;
import com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions.IncorrectDataException;

import static java.util.Objects.isNull;

public class SpeedStateCheckerImpl implements SpeedStateChecker{

    @Override
    public void check(SpeedState2D state) {
        if (isNull(state.getX()) || isNull(state.getY())) {
            throw new IncorrectDataException(this.getClass().getCanonicalName(), "SpeedState содержит не корректнче данные");
        }
    }
}
