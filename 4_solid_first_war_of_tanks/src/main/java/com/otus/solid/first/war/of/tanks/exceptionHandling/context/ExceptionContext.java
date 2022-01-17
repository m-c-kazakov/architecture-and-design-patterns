package com.otus.solid.first.war.of.tanks.exceptionHandling.context;

import com.otus.solid.first.war.of.tanks.actions.Action;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Queue;

/**
 * Сожержит данные достаточные для обработки исключения
 */
@Builder
@Getter
@ToString
public class ExceptionContext {
    Exception exception;
    Action command;
    Queue<Action> actionsQueue;
}
