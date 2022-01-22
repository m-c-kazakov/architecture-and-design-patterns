package com.otus.solid.first.war.of.tanks.actions.state.checkers;

import com.otus.solid.first.war.of.tanks.actions.state.location.LocationState2d;
import com.otus.solid.first.war.of.tanks.exceptions.IncorrectDataException;

import static java.util.Objects.isNull;

public class LocationStateCheckerImpl implements LocationStateChecker{
    @Override
    public boolean isNeedToCheck(LocationState2d state) {
        return state.getCheckersName().stream().anyMatch("LocationState2dChecker"::equals);
    }

    @Override
    public void check(LocationState2d s) {
        if (isNull(s.getX()) || isNull(s.getY())) {
            throw new IncorrectDataException("LocationState2d содержит не корректнче данные");
        }

    }
}
