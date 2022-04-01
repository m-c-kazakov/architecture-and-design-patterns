package com.otus.solid.first.war.of.tanks.collision;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions.CollisionMoveException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CollisionDeterminer implements Action {

    GameObject first;
    GameObject second;

    @Override
    public void execute() {
        if (first.getAreaPoints().stream().anyMatch(areaPoint2D -> second.getAreaPoints().contains(areaPoint2D))) {
            throw new CollisionMoveException("CollisionDeterminant2D", "Столкновение 2 игровых объектов");
        }
    }
}
