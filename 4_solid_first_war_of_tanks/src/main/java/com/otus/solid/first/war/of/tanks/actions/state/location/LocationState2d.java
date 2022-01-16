package com.otus.solid.first.war.of.tanks.actions.state.location;

import com.otus.solid.first.war.of.tanks.actions.State;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class LocationState2d implements State {
    private List<String> checkersName;
    private Long x;
    private Long y;
}
