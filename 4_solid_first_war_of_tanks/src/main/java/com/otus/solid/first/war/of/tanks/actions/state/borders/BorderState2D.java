package com.otus.solid.first.war.of.tanks.actions.state.borders;

import com.otus.solid.first.war.of.tanks.actions.CheckerOwner;
import com.otus.solid.first.war.of.tanks.actions.State;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.BorderStateChecker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Определяет границы по в рамках которых может вдигваться объект
 */
@Getter
@Setter
@AllArgsConstructor
public class BorderState2D implements State, CheckerOwner<BorderStateChecker> {

    List<BorderStateChecker> checkers;
    Long rightBorder;
    Long leftBorder;
    Long upperBound;
    Long lowerBound;


    public boolean isLocatedIdBorders(Long x) {
        return leftBorder < x && rightBorder > x;
    }

    public boolean isLocatedInBound(Long y) {
        return lowerBound < y && upperBound > y;
    }

    @Override
    public void check() {
        checkers.forEach(stateChecker -> stateChecker.check(this));
    }
}
