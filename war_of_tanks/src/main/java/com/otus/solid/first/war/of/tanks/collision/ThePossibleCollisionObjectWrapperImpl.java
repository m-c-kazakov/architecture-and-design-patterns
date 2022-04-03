package com.otus.solid.first.war.of.tanks.collision;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ThePossibleCollisionObjectWrapperImpl implements ThePossibleCollisionObjectWrapper {

    Set<String> polledAreas;
    Queue<CollisionDeterminer> collisionDeterminers;


    @Override
    public void addPolledAreaObjectName(Set<String> areaObjectName) {
        this.polledAreas.addAll(areaObjectName);

    }

    @Override
    public Set<String> whichOfThemAreaObjectsShouldBePolled(Set<String> areaObjectsName) {
        return areaObjectsName.stream().filter(areaName -> !polledAreas.contains(areaName)).collect(Collectors.toSet());
    }


    @Override
    public void addPossibleCollisionGameObject(CollisionDeterminer collisionDeterminer) {
        this.collisionDeterminers.add(collisionDeterminer);
    }
}
