package com.otus.solid.first.war.of.tanks.collision;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

/**
 * Заглушка игрового объекта
 *
 *
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GameObject {
    /**
     * Точки в пространстве, в рамках которых находиться игровой объект
     */
    Set<String> areaPoints;

    public boolean isLocatedInd(String point) {
        return areaPoints.contains(point);
    }
}
