package com.otus.solid.first.war.of.tanks.actions.state.turn;

import com.otus.solid.first.war.of.tanks.actions.State;

import java.util.Map;

public interface TurnState extends State {

    Object getCurrentDirectionByName(String name);
    void putCurrentDirection(String name, Object value);

    Object getDesiredDirection(String name);
    Map<String, Object> getAllDesiredDirection();
    void putDesiredDirection(String name, Object value);

}
