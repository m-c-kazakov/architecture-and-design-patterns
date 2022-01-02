package com.otus.solid.first.war.of.tanks.actions.state.speed;

import lombok.Builder;

@Builder
public class SpeedStateImpl implements SpeedState{

    private final String checkerName;
    private Long x;
    private Long y;

    @Override
    public String getCheckerName() {
        return checkerName;
    }

    @Override
    public Long getX() {
        return x;
    }

    @Override
    public void setX(Long x) {
        this.x = x;
    }

    @Override
    public Long getY() {
        return y;
    }

    @Override
    public void setY(Long y) {
        this.y = y;
    }
}
