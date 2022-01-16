package com.otus.solid.first.war.of.tanks.actions.state.borders;

import com.otus.solid.first.war.of.tanks.actions.State;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.BorderStateChecker;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.Checker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

/**
 * Определяет границы по в рамках которых может вдигваться объект
 */
@Getter
@Setter
@AllArgsConstructor
public class BorderState2D implements State<BorderStateChecker> {

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
        checkers.stream().filter(stateChecker -> stateChecker.isNeedToCheck(this)).forEach(stateChecker -> stateChecker.check(this));
    }
}
