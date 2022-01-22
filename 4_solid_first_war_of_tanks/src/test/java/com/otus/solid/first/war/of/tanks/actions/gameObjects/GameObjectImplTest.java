package com.otus.solid.first.war.of.tanks.actions.gameObjects;

import com.otus.solid.first.war.of.tanks.actions.changers.Changer;
import com.otus.solid.first.war.of.tanks.actions.changers.MicroMoveChanger;
import com.otus.solid.first.war.of.tanks.actions.move.MovementImpl;
import com.otus.solid.first.war.of.tanks.actions.state.borders.BorderState2D;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.LocationStateCheckerImpl;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.MovementStateChecker;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.MovementStateCheckerImpl;
import com.otus.solid.first.war.of.tanks.actions.state.checkers.SpeedStateCheckerImpl;
import com.otus.solid.first.war.of.tanks.actions.state.location.LocationState2d;
import com.otus.solid.first.war.of.tanks.actions.state.movement.MovementState2d;
import com.otus.solid.first.war.of.tanks.actions.state.speed.SpeedState2D;
import com.otus.solid.first.war.of.tanks.exceptionHandling.BaseExceptionsHandlerImpl;
import com.otus.solid.first.war.of.tanks.exceptionHandling.excrptionHandlers.ImpossibleActionExceptionHandler;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class GameObjectImplTest {

    @Test
    void execute() {
        LocationState2d locationState2d = LocationState2d.builder().checkers(List.of((new LocationStateCheckerImpl()))).x(null).y(null).build();
        String movementName = "Movement";
        String changerName = "Changer";

        SpeedState2D speedState = new SpeedState2D(List.of(new SpeedStateCheckerImpl()), -7L, 3L);

        MovementStateChecker movementStateChecker = new MovementStateCheckerImpl(new BorderState2D(Collections.emptyList(), 40L, -40L, 70L, -70L));
        MovementState2d state = new MovementState2d(List.of(movementStateChecker), locationState2d, speedState);
        MovementImpl movement = new MovementImpl(state);

        GameObjectImpl gameObject = new GameObjectImpl();
        gameObject.setActions(Map.of(movementName, movement));
        gameObject.setActionsQueue(new LinkedList<>());
        BaseExceptionsHandlerImpl baseExceptionsHandler = new BaseExceptionsHandlerImpl();
        baseExceptionsHandler.setExceptionHandler(List.of(new ImpossibleActionExceptionHandler()));
        gameObject.setBaseExceptionsHandler(baseExceptionsHandler);

        Changer moveChanger = new MicroMoveChanger();
        gameObject.setChangers(Map.of(changerName, moveChanger));

        gameObject.setActionsQueue(movementName, changerName);
        gameObject.execute();
        gameObject.execute();
    }
}