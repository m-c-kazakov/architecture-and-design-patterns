package com.otus.solid.first.war.of.tanks.collision;

import com.otus.solid.first.war.of.tanks.iocResolvers.IoC;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GameMap {

    Collection<AreaObject> areaObjects;
    String scopeId;


    public void determinateCollisionGameObjects() {
        ThePossibleCollisionObjectWrapper thePossibleCollisionObjectWrapper = IoC.resolve(Map.of("dependencyName", "ThePossibleCollisionObjectWrapper", "scopeId", scopeId));
        areaObjects.forEach(areaObject -> areaObject.addCollisionObjectsIfExist(thePossibleCollisionObjectWrapper));
    }

    public void changeAreaObjectForGameObject() {
        areaObjects.forEach(AreaObject::changeAreaObjectForGameObjectIfChangeAreaPoint);
    }
}
