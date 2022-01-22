package com.otus.solid.first.war.of.tanks.actions.state.speed;

import com.otus.solid.first.war.of.tanks.actions.State;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SpeedState2D implements State {
    private List<String> checkersName;
    private Long x;
    private Long y;
}
