package com.otus.solid.first.war.of.tanks.actions.borders;

import com.otus.solid.first.war.of.tanks.actions.State;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Определяет границы по в рамках которых может вдигваться объект
 */
@Getter
@AllArgsConstructor
public class BorderState2D implements State {

    List<String> checkersName;
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


}
