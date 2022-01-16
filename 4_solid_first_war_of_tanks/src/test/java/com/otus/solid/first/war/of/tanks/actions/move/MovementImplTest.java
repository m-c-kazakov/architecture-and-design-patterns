package com.otus.solid.first.war.of.tanks.actions.move;


import com.otus.solid.first.war.of.tanks.actions.State;
import com.otus.solid.first.war.of.tanks.actions.borders.BorderState2D;
import com.otus.solid.first.war.of.tanks.actions.state.changers.MoveChanger;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.*;
import com.otus.solid.first.war.of.tanks.actions.state.location.LocationState2d;
import com.otus.solid.first.war.of.tanks.actions.state.movement.MovementState2d;
import com.otus.solid.first.war.of.tanks.actions.state.speed.SpeedState2D;
import com.otus.solid.first.war.of.tanks.exceptions.ImpossibleActionException;
import com.otus.solid.first.war.of.tanks.exceptions.IncorrectDataException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
        LocationState2d locationState2d = LocationState2d.builder().checkersName(List.of("LocationState2dChecker")).x(12L).y(5L).build();
        SpeedState2D speedState = new SpeedState2D(List.of("SpeedState2DChecker"), -7L, 3L);
        MovementState2d state = new MovementState2d(List.of("MovementState2dChecker"), locationState2d, speedState);

        ArrayList<Checker<State>> checkers = new ArrayList<>();
        MovementImpl movement = new MovementImpl(state, checkers);

        movement.execute(new MoveChanger());
        LocationState2d locationState = state.getLocationState2d();
        assertEquals(5L, locationState.getX(), "Значение X Не соответствует ожидаемому: 5");
        assertEquals(8L, locationState.getY(), "Значение y Не соответствует ожидаемому: 8");

    }

    @Test
    @DisplayName("Попытка сдвинуть объект, у которого невозможно прочитать положение в пространстве, приводит к ошибке")
    void execute$2() {
        LocationState2d locationState2d = LocationState2d.builder().checkersName(List.of("LocationState2dChecker")).x(null).y(null).build();
        SpeedState2D speedState = new SpeedState2D(List.of("SpeedState2DChecker"), -7L, 3L);
        MovementState2d state = new MovementState2d(List.of("MovementStateChecker"), locationState2d, speedState);

        List<Checker<State>> checkers = new ArrayList<>();
        MovementStateChecker movementStateChecker = new MovementStateCheckerImpl(new BorderState2D(Collections.emptyList(), 40L, -40L, 70L, -70L), List.of(new LocationStateCheckerImpl()), List.of(new SpeedStateCheckerImpl()));
        checkers.add((Checker) movementStateChecker);
        MovementImpl movement = new MovementImpl(state, checkers);

        assertThrows(IncorrectDataException.class, () -> movement.execute(new MoveChanger()), "Ожидаемое поведение - ошибка IncorrectDataException");
    }

    @Test
    @DisplayName("Попытка сдвинуть объект, у которого невозможно прочитать значение мгновенной скорости, приводит к ошибке")
    void execute$3() {
        LocationState2d locationState2d = LocationState2d.builder().checkersName(List.of("LocationState2dChecker")).x(12L).y(5L).build();
        SpeedState2D speedState = new SpeedState2D(List.of("SpeedState2DChecker"), null, null);
        MovementState2d state = new MovementState2d(List.of("MovementStateChecker"), locationState2d, speedState);

        List<Checker<State>> checkers = new ArrayList<>();
        MovementStateChecker movementStateChecker = new MovementStateCheckerImpl(new BorderState2D(Collections.emptyList(), 40L, -40L, 70L, -70L), List.of(new LocationStateCheckerImpl()), List.of(new SpeedStateCheckerImpl()));
        checkers.add((Checker) movementStateChecker);
        MovementImpl movement = new MovementImpl(state, checkers);

        assertThrows(IncorrectDataException.class, () -> movement.execute(new MoveChanger()), "Ожидаемое поведение - ошибка IncorrectDataException");
    }

    @Test
    @DisplayName("Попытка сдвинуть объект, у которого невозможно изменить положение в пространстве, приводит к ошибке")
    void execute$4() {

        LocationState2d locationState2d = LocationState2d.builder().checkersName(List.of("LocationState2dChecker")).x(12L).y(5L).build();
        SpeedState2D speedState = new SpeedState2D(List.of("SpeedState2DChecker"), -7L, 3L);
        MovementState2d state = new MovementState2d(List.of("MovementStateChecker"), locationState2d, speedState);

        List<Checker<State>> checkers = new ArrayList<>();
        MovementStateChecker movementStateChecker = new MovementStateCheckerImpl(new BorderState2D(Collections.emptyList(), 4L, -4L, 7L, -7L), List.of(new LocationStateCheckerImpl()), List.of(new SpeedStateCheckerImpl()));
        checkers.add((Checker) movementStateChecker);
        MovementImpl movement = new MovementImpl(state, checkers);

        assertThrows(ImpossibleActionException.class, () -> movement.execute(new MoveChanger()), "Ожидаемое поведение - ошибка IncorrectDataException");

    }
}