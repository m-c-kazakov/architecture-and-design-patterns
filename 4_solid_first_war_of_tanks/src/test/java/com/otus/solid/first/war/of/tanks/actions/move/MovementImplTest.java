package com.otus.solid.first.war.of.tanks.actions.move;


import com.otus.solid.first.war.of.tanks.actions.State;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.Checker;
import com.otus.solid.first.war.of.tanks.actions.state.location.LocationState;
import com.otus.solid.first.war.of.tanks.actions.state.location.LocationStateImpl;
import com.otus.solid.first.war.of.tanks.actions.state.movement.MovementState;
import com.otus.solid.first.war.of.tanks.actions.state.movement.MovementStateImpl;
import com.otus.solid.first.war.of.tanks.actions.state.speed.SpeedState;
import com.otus.solid.first.war.of.tanks.actions.state.speed.SpeedStateImpl;
import com.otus.solid.first.war.of.tanks.exceptions.ImpossibleActionException;
import com.otus.solid.first.war.of.tanks.exceptions.IncorrectDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovementImplTest {

    private MovementImpl movement;
    @Mock
    HashMap<String, Checker<State>> checkers;

    @BeforeEach
    void setUp() {

        movement = new MovementImpl(checkers);

    }

    /**
     * Для объекта, находящегося в точке (12, 5) и движущегося со скоростью (-7, 3) движение меняет положение объекта на (5, 8)
     */

    @Test
    @DisplayName("Для объекта, находящегося в точке (12, 5) и движущегося со скоростью (-7, 3) движение меняет положение объекта на (5, 8)")
    void execute$1() {
        when(checkers.get(null)).thenReturn(state -> {
        });

        MovementStateImpl state = MovementStateImpl
                .builder()
                .locationState(LocationStateImpl
                        .builder()
                        .x(12L)
                        .y(5L)
                        .build())
                .speedState(SpeedStateImpl
                        .builder()
                        .x(-7L)
                        .y(3L)
                        .build())
                .build();
        movement.execute(state);
        LocationState locationState = state.getLocationState();
        assertEquals(5L, locationState.getX(), "Значение X Не соответствует ожидаемому: 5");
        assertEquals(8L, locationState.getY(), "Значение y Не соответствует ожидаемому: 8");

    }

    @Test
    @DisplayName("Попытка сдвинуть объект, у которого невозможно прочитать положение в пространстве, приводит к ошибке")
    void execute$2() {
        String checkerName = "locationState";
        when(checkers.get(checkerName)).thenReturn(state -> {
            LocationState s = (LocationState) state;
            if (isNull(s.getX()) || isNull(s.getY())) {
                throw new IncorrectDataException("LocationState содержит не корректнче данные");
            }
        });

        MovementStateImpl state = MovementStateImpl
                .builder()
                .locationState(LocationStateImpl
                        .builder()
                        .x(null)
                        .y(null)
                        .checkerName(checkerName)
                        .build())
                .speedState(SpeedStateImpl
                        .builder()
                        .x(-7L)
                        .y(3L)
                        .build())
                .build();

        assertThrows(IncorrectDataException.class, () -> movement.execute(state), "Ожидаемое поведение - ошибка IncorrectDataException");
    }

    @Test
    @DisplayName("Попытка сдвинуть объект, у которого невозможно прочитать значение мгновенной скорости, приводит к ошибке")
    void execute$3() {
        String checkerName = "seedState";
        when(checkers.get(null)).thenReturn(state -> {
        });
        when(checkers.get(checkerName)).thenReturn(state -> {
            SpeedState s = (SpeedState) state;
            if (isNull(s.getX()) || isNull(s.getY())) {
                throw new IncorrectDataException("SpeedState содержит не корректнче данные");
            }
        });

        MovementStateImpl state = MovementStateImpl
                .builder()
                .locationState(LocationStateImpl
                        .builder()
                        .x(12L)
                        .y(5L)
                        .build())
                .speedState(SpeedStateImpl
                        .builder()
                        .x(null)
                        .y(null)
                        .checkerName(checkerName)
                        .build())
                .build();

        assertThrows(IncorrectDataException.class, () -> movement.execute(state), "Ожидаемое поведение - ошибка IncorrectDataException");
    }

    @Test
    @DisplayName("Попытка сдвинуть объект, у которого невозможно изменить положение в пространстве, приводит к ошибке")
    void execute$4() {
        when(checkers.get(null)).thenReturn(state -> {
        });
        String checkerName = "movementState";
        when(checkers.get(checkerName)).thenReturn(state -> {
            MovementState ms = (MovementState) state;
            LocationState locationState = ms.getLocationState();
            SpeedState speedState = ms.getSpeedState();
            Long x = locationState.getX() + speedState.getX();
            Long y = locationState.getY() + speedState.getY();

            if (x > 4 || y > 7) {
                throw new ImpossibleActionException("Не возможно изменить положение в пространстве");
            }

        });

        MovementStateImpl state = MovementStateImpl
                .builder()
                .locationState(LocationStateImpl
                        .builder()
                        .x(12L)
                        .y(5L)
                        .build())
                .speedState(SpeedStateImpl
                        .builder()
                        .x(-7L)
                        .y(3L)
                        .build())
                .checkerName(checkerName)
                .build();

        assertThrows(ImpossibleActionException.class, () -> movement.execute(state), "Ожидаемое поведение - ошибка IncorrectDataException");

    }

    @Test
    @DisplayName("")
    void execute$5() {

    }
}