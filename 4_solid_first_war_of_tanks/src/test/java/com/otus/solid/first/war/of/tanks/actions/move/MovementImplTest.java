package com.otus.solid.first.war.of.tanks.actions.move;


import com.otus.solid.first.war.of.tanks.actions.State;
import com.otus.solid.first.war.of.tanks.actions.state.borders.BorderState2D;
import com.otus.solid.first.war.of.tanks.actions.changers.MoveChanger;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.*;
import com.otus.solid.first.war.of.tanks.actions.state.location.LocationState2d;
import com.otus.solid.first.war.of.tanks.actions.state.movement.MovementState2d;
import com.otus.solid.first.war.of.tanks.actions.state.speed.SpeedState2D;
import com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions.ImpossibleActionException;
import com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions.IncorrectDataException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MovementImplTest {

    @Test
    @DisplayName("Для объекта, находящегося в точке (12, 5) и движущегося со скоростью (-7, 3) движение меняет положение объекта на (5, 8)")
    void execute$1() {
        LocationState2d locationState2d = LocationState2d.builder().checkers(Collections.emptyList()).x(12L).y(5L).build();
        SpeedState2D speedState = new SpeedState2D(Collections.emptyList(), -7L, 3L);
        MovementState2d state = new MovementState2d(List.of(), locationState2d, speedState);

        MovementImpl movement = new MovementImpl(state);
        movement.setChanger(new MoveChanger());
        movement.execute();
        LocationState2d locationState = state.getLocationState2d();
        assertEquals(5L, locationState.getX(), "Значение X Не соответствует ожидаемому: 5");
        assertEquals(8L, locationState.getY(), "Значение y Не соответствует ожидаемому: 8");

    }

    @Test
    @DisplayName("Попытка сдвинуть объект, у которого невозможно прочитать положение в пространстве, приводит к ошибке")
    void execute$2() {
        LocationState2d locationState2d = LocationState2d.builder().checkers(List.of((new LocationStateCheckerImpl()))).x(null).y(null).build();
        SpeedState2D speedState = new SpeedState2D(List.of(new SpeedStateCheckerImpl()), -7L, 3L);

        MovementStateChecker movementStateChecker = new MovementStateCheckerImpl(new BorderState2D(Collections.emptyList(), 40L, -40L, 70L, -70L));
        MovementState2d state = new MovementState2d(List.of(movementStateChecker), locationState2d, speedState);
        MovementImpl movement = new MovementImpl(state);
        movement.setChanger(new MoveChanger());
        assertThrows(IncorrectDataException.class, movement::execute, "Ожидаемое поведение - ошибка IncorrectDataException");
    }

    @Test
    @DisplayName("Попытка сдвинуть объект, у которого невозможно прочитать значение мгновенной скорости, приводит к ошибке")
    void execute$3() {
        LocationState2d locationState2d = LocationState2d.builder().checkers(List.of(new LocationStateCheckerImpl())).x(12L).y(5L).build();
        SpeedState2D speedState = new SpeedState2D(List.of(new SpeedStateCheckerImpl()), null, null);

        MovementStateChecker movementStateChecker = new MovementStateCheckerImpl(new BorderState2D(Collections.emptyList(),
                40L, -40L, 70L, -70L));
        MovementState2d state = new MovementState2d(List.of(movementStateChecker), locationState2d, speedState);
        MovementImpl movement = new MovementImpl(state);
        movement.setChanger(new MoveChanger());
        assertThrows(IncorrectDataException.class, () -> movement.execute(), "Ожидаемое поведение - ошибка IncorrectDataException");
    }

    @Test
    @DisplayName("Попытка сдвинуть объект, у которого невозможно изменить положение в пространстве, приводит к ошибке")
    void execute$4() {

        LocationState2d locationState2d = LocationState2d.builder().checkers(List.of(new LocationStateCheckerImpl())).x(12L).y(5L).build();
        SpeedState2D speedState = new SpeedState2D(List.of(new SpeedStateCheckerImpl()), -7L, 3L);

        MovementStateChecker movementStateChecker = new MovementStateCheckerImpl(new BorderState2D(Collections.emptyList(),
                4L, -4L, 7L, -7L));
        MovementState2d state = new MovementState2d(List.of(movementStateChecker), locationState2d, speedState);
        MovementImpl movement = new MovementImpl(state);
        movement.setChanger(new MoveChanger());
        assertThrows(ImpossibleActionException.class, () -> movement.execute(), "Ожидаемое поведение - ошибка IncorrectDataException");

    }
}