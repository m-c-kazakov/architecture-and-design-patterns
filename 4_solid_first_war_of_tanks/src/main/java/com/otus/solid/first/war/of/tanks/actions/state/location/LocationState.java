package com.otus.solid.first.war.of.tanks.actions.state.location;

import com.otus.solid.first.war.of.tanks.actions.State;

public interface LocationState extends State {

    Long getX();

    void setX(Long x);

    Long getY();

    void setY(Long y);
}
