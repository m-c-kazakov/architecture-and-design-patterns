package com.otus.solid.first.war.of.tanks.collision;

import com.otus.solid.first.war.of.tanks.actions.Action;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * определяет окрестность, в которой присутствует игровой объект,если объект попал в новую окрестность, то удаляет его из списка объектов старой окрестности и добавляет список объектов новой окрестности.
 */
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChangeAreaCommand implements Action {

    AreaObject areaObject;

    @Override
    public void execute() {
        areaObject.changeAreaObjectForGameObjectIfChangeAreaPoint();
    }
}
