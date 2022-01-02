package com.otus.solid.first.war.of.tanks.actions.state.speed;

import com.otus.solid.first.war.of.tanks.actions.State;

public interface SpeedState extends State {
    Long getX();

    void setX(Long x);

    Long getY();

    void setY(Long y);
}
