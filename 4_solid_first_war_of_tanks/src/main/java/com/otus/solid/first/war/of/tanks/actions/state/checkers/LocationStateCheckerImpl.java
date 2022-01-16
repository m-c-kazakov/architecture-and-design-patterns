package com.otus.solid.first.war.of.tanks.actions.state.checkers;

import com.otus.solid.first.war.of.tanks.actions.state.location.LocationState2d;
import com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions.IncorrectDataException;

import java.util.Objects;

import static java.util.Objects.isNull;

public class LocationStateCheckerImpl implements LocationStateChecker{
    // todo джобавить состояние командам
    @Override
    public boolean isNeedToCheck(LocationState2d state) {
        return Objects.nonNull(state);
    }

    @Override
    public void check(LocationState2d s) {
        if (isNull(s.getX()) || isNull(s.getY())) {
            throw new IncorrectDataException(this.getClass().getCanonicalName(), "LocationState2d содержит не корректнче данные");
        }

    }
}
