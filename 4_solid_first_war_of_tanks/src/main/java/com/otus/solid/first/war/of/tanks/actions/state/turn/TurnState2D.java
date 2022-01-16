package com.otus.solid.first.war.of.tanks.actions.state.turn;

import com.otus.solid.first.war.of.tanks.actions.State;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TurnState2D implements State {

    private final List<String> checkersName;
    private String currentDirection;
}
