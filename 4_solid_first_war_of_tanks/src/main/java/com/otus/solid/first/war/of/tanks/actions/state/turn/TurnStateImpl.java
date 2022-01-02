package com.otus.solid.first.war.of.tanks.actions.state.turn;

import lombok.Builder;

import java.util.Map;

@Builder
public class TurnStateImpl implements TurnState{

    private final String checkerName;
    private final Map<String, Object> currentDirection;
    private final Map<String, Object> desiredDirection;

    @Override
    public String getCheckerName() {
        return checkerName;
    }

    @Override
    public Object getCurrentDirectionByName(String name) {
        return currentDirection.get(name);
    }

    @Override
    public void putCurrentDirection(String name, Object value) {
        this.currentDirection.put(name, value);

    }

    @Override
    public Object getDesiredDirection(String name) {
        return desiredDirection.get(name);
    }

    @Override
    public Map<String, Object> getAllDesiredDirection() {
        return desiredDirection;
    }

    @Override
    public void putDesiredDirection(String name, Object value) {
        this.desiredDirection.put(name, value);
    }
}
