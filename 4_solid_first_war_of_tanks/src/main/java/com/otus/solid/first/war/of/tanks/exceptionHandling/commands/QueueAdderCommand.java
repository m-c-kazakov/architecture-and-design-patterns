package com.otus.solid.first.war.of.tanks.exceptionHandling.commands;

import com.otus.solid.first.war.of.tanks.actions.Action;
import lombok.Builder;

import java.util.Queue;

@Builder
public class QueueAdderCommand implements Action {

    private Action action;
    private Queue<Action> actionQueue;

    @Override
    public void execute() {
        actionQueue.add(action);
    }
}
