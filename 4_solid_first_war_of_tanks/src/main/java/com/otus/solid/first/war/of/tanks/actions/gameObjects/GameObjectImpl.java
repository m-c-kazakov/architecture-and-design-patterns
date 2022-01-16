package com.otus.solid.first.war.of.tanks.actions.gameObjects;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.actions.State;
import com.otus.solid.first.war.of.tanks.actions.changers.Changer;
import com.otus.solid.first.war.of.tanks.exceptionHandling.BaseExceptionsHandler;
import com.otus.solid.first.war.of.tanks.exceptionHandling.context.ExceptionContext;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.Queue;

import static java.util.Optional.ofNullable;

@Setter
@AllArgsConstructor
public class GameObjectImpl implements GameObject{

    private Map<String, Action<Changer<State>>> actions;
    private Map<String, Changer<State>> changers;
    private BaseExceptionsHandler baseExceptionsHandler;

    private Queue<Action<Changer<State>>> actionsQueue;

    public void execute() {

        Action<Changer<State>> action = actionsQueue.poll();
        try {
            ofNullable(action).ifPresent(Action::execute);
        } catch (Exception exception) {
            baseExceptionsHandler.processing(ExceptionContext.builder().exception(exception).command(action).build());
        }

    }

    @Override
    public void setActionsQueue(String actionName, String changerName) {
        Action<Changer<State>> action = actions.get(actionName);
        action.setChanger(changers.get(changerName));
        actionsQueue.add(action);
    }
}
