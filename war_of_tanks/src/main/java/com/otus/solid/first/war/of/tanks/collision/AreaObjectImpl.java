package com.otus.solid.first.war.of.tanks.collision;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AreaObjectImpl implements AreaObject {

    String name;
    Set<GameObject> gameObjects;
    /**
     * Набор квадратов игрового поля описанных в формате: 0.0_0.1|1.0_1.1
     * Где все что до | верхние координаты
     * Все что после | нижние координаты
     * <p>
     * Все что левее _ Левая координата
     * Все что правее _ Правая координата
     */
    Set<String> areaPoints;
    Set<AreaObject> neighbors= new HashSet<>();

    @Override
    public void addCollisionObjectsIfExist(ThePossibleCollisionObjectWrapper collisionObject) {

        addCollisionGameObjectsInCurrentArea(collisionObject);
        addCollisionGameObjectsInNeignborsArea(collisionObject);
    }

    private void addCollisionGameObjectsInNeignborsArea(ThePossibleCollisionObjectWrapper collisionObject) {
        Set<String> neighborsAreaObjectsName = neighbors.stream().map(AreaObject::getName).collect(Collectors.toSet());
        Set<String> areaObjectsForPolled = collisionObject.whichOfThemAreaObjectsShouldBePolled(neighborsAreaObjectsName);
        neighbors.stream()
                .filter(neighbor -> areaObjectsForPolled.contains(neighbor.getName()))
                .flatMap(neighbor -> neighbor.returnGameObjectsThatThisArea(areaPoints).stream()
                        .flatMap(gameObject -> this.gameObjects.stream().map(currentAreaGameObject -> new CollisionDeterminer(currentAreaGameObject, gameObject))))
                .forEach(collisionObject::addPossibleCollisionGameObject);

        collisionObject.addPolledAreaObjectName(neighborsAreaObjectsName);
    }

    private void addCollisionGameObjectsInCurrentArea(ThePossibleCollisionObjectWrapper collisionObject) {
        ArrayList<GameObject> gameObjects = new ArrayList<>(this.gameObjects);
        if (gameObjects.size() > 1) {
            for (int i = 0; i < gameObjects.size(); i++) {
                for (int j = 1; j < gameObjects.size(); j++) {
                    if (gameObjects.get(j).equals(gameObjects.get(i))) {
                        continue;
                    }
                    collisionObject.addPossibleCollisionGameObject(new CollisionDeterminer(gameObjects.get(i), gameObjects.get(j)));
                }
            }
        }
    }


    @Override
    public void addGameObject(GameObject gameObject) {
        this.gameObjects.add(gameObject);

    }

    @Override
    public void removeGameObject(GameObject gameObject) {
        this.gameObjects.remove(gameObject);

    }

    @Override
    public void addNeighbors(Set<AreaObject> neighbors) {
        this.neighbors.addAll(neighbors);
    }

    @Override
    public Set<GameObject> returnGameObjectsThatThisArea(Set<String> areaPoints) {
        return areaPoints.stream()
                .filter(this.areaPoints::contains)
                .flatMap(point -> gameObjects.stream().filter(gameObject -> gameObject.isLocatedInd(point)))
                .collect(Collectors.toSet());
    }

    @Override
    public void changeAreaObjectForGameObjectIfChangeAreaPoint() {
        neighbors.forEach(areaObject -> new ArrayList<>(gameObjects).stream()
                        .filter(gameObject -> areaObject.isContainAreaPoints(gameObject.getAreaPoints()))
                        .forEach(gameObject -> {
                            areaObject.addGameObject(gameObject);
                            this.gameObjects.remove(gameObject);
                        }));
    }

    @Override
    public boolean isContainAreaPoints(Set<String> areaPoints) {
        return areaPoints.stream().anyMatch(this.areaPoints::contains);
    }
}
