package com.otus.solid.first.war.of.tanks.collision;

import java.util.Set;

/**
 * Окрестность
 *
 * Содержит информацию о находящихся внутри нее игровых объектах
 *
 * Спрашивает у соседа есть ли Игровые объекты на спорной области
 *
 * Спрашивает CollisionObjectWrapper к каким соседям нужно сходить
 *
 * Отвечает соседям
 *
 */
public interface AreaObject {


    void addCollisionObjectsIfExist(ThePossibleCollisionObjectWrapper collisionObject);

    void addGameObject(GameObject gameObject);

    void removeGameObject(GameObject gameObject);

    String getName();


    void addNeighbors(Set<AreaObject> neighbors);

    Set<GameObject> returnGameObjectsThatThisArea(Set<String> areaPoints);

    void changeAreaObjectForGameObjectIfChangeAreaPoint();

    boolean isContainAreaPoints(Set<String> areaPoints);
}
