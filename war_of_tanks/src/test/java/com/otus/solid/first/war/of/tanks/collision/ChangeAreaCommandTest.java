package com.otus.solid.first.war.of.tanks.collision;

import com.otus.solid.first.war.of.tanks.iocResolvers.IoC;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class ChangeAreaCommandTest {

    @Test
    @DisplayName("определяет окрестность, в которой присутствует игровой объект,если объект попал в новую окрестность, то удаляет его из списка объектов старой окрестности и добавляет список объектов новой окрестности.")
    void execute() {

        Function<Map<String, Object>, Queue<CollisionDeterminer>> initFuncСollisionDeterminers = (map) -> new LinkedList<>();;
        IoC.resolve(Map.of("dependencyName", "СollisionDeterminers", "initFunc", initFuncСollisionDeterminers,  "scopeId", "GameMapTest", "isSingleton", true));

        Function<Map<String, Object>, ThePossibleCollisionObjectWrapper> initFuncThePossibleCollisionObjectWrapper = (map) -> new ThePossibleCollisionObjectWrapperImpl(new HashSet<>(), IoC.resolve(Map.of("dependencyName", "СollisionDeterminers", "scopeId", map.get("scopeId"))));;
        IoC.resolve(Map.of("dependencyName", "ThePossibleCollisionObjectWrapper", "initFunc", initFuncThePossibleCollisionObjectWrapper,  "scopeId", "GameMapTest"));


        AreaObjectImpl areaObject_1_1 = new AreaObjectImpl("AreaObject_1.1", new HashSet<>(Set.of(new GameObject(Set.of("0.2_0.3|1.2_1.3")))),
                Set.of("0.0_0.1|1.0_1.1", "0.1_0.2|1.1_1.2",
                        "1.0_1.1|2.0_2.1", "1.1_1.2|2.1_2.2"));
        AreaObjectImpl areaObject_1_2 = new AreaObjectImpl("AreaObject_1.2", new HashSet<>(Set.of(new GameObject(Set.of("0.1_0.2|1.1_1.2")))), Set.of("0.1_0.2|1.1_1.2", "0.2_0.3|1.2_1.3",
                "1.1_1.2|2.1_2.2", "1.2_1.3|2.2_2.3"));

        AreaObject areaObject_2_1 = new AreaObjectImpl("AreaObject_2.1", Set.of(), Set.of("1.0_1.1|2.0_2.1", "1.1_1.2|2.1_2.2",
                "2.0_2.1|3.0_3.1", "2.1_2.2|3.1_3.2"));
        AreaObject areaObject_2_2 = new AreaObjectImpl("AreaObject_2.2", Set.of(), Set.of("1.1_1.2|2.1_2.2", "1.2_1.3|2.2_2.3",
                "2.1_2.2|3.1_3.2", "2.2_2.3|3.2_3.3"));

        areaObject_1_1.addNeighbors(Set.of(areaObject_1_2, areaObject_2_1));
        areaObject_1_2.addNeighbors(Set.of(areaObject_1_1, areaObject_2_2));
        areaObject_2_1.addNeighbors(Set.of(areaObject_1_1, areaObject_2_2));
        areaObject_2_2.addNeighbors(Set.of(areaObject_1_2, areaObject_2_1));

        ChangeAreaCommand changeAreaCommand = new ChangeAreaCommand(areaObject_1_1);
        assertEquals(1, areaObject_1_1.getGameObjects().size());
        assertEquals(1, areaObject_1_2.getGameObjects().size());
        changeAreaCommand.execute();
        assertEquals(0, areaObject_1_1.getGameObjects().size());
        assertEquals(2, areaObject_1_2.getGameObjects().size());

    }
}