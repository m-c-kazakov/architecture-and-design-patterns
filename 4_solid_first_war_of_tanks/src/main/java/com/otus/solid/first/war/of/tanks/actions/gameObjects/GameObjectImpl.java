package com.otus.solid.first.war.of.tanks.actions.gameObjects;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.actions.ChangeOwner;
import com.otus.solid.first.war.of.tanks.actions.Command;
import com.otus.solid.first.war.of.tanks.actions.State;
import com.otus.solid.first.war.of.tanks.exceptionHandling.BaseExceptionsHandler;
import com.otus.solid.first.war.of.tanks.exceptionHandling.context.ExceptionContext;
import lombok.Setter;

import java.util.Map;
import java.util.Queue;

import static java.util.Optional.ofNullable;

@Setter
public class GameObjectImpl implements GameObject{

    private Map<String, Action> actions;
    private Map<String, Command<State>> changers;
    private BaseExceptionsHandler baseExceptionsHandler;

    private Queue<Action> actionsQueue;
    @Override
    public void execute() {

        Action action = actionsQueue.poll();
        try {
            ofNullable(action).ifPresent(Action::execute);
        } catch (Exception exception) {
            baseExceptionsHandler.processing(ExceptionContext.builder()
                    .exception(exception)
                    .command(action)
                    .actionsQueue(actionsQueue)
                    .build());
        }

    }

    @Override
    public void setActionsQueue(String actionName, String changerName) {
        Action action = actions.get(actionName);
        ChangeOwner changeOwner = (ChangeOwner) action;
        changeOwner.setChanger(changers.get(changerName));
        actionsQueue.add(action);
    }
}
