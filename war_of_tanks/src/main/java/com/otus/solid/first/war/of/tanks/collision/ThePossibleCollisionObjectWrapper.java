package com.otus.solid.first.war.of.tanks.collision;

import java.util.Set;

/**
 * Хранитель информации о прохождении процесса поиска коллизий
 *
 * Сохраняет информацию об опрошенных окрестностях
 * Отдает информацию о том, какие окрестности не опрошены из перечня представленных
 *
 * Сохраняет название окрестностей, в которых возможна коллизия.
 * Дает доступ к данным окрестностей0, в которых возможна коллизия.
 *
 */
public interface ThePossibleCollisionObjectWrapper {


    void addPolledAreaObjectName(Set<String> areaObjectNames);

    Set<String> whichOfThemAreaObjectsShouldBePolled(Set<String> areaObjectsName);

    void addPossibleCollisionGameObject(CollisionDeterminer collisionDeterminer);
}
