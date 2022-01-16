package com.otus.solid.first.war.of.tanks.exceptionHandling.context;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.actions.State;
import com.otus.solid.first.war.of.tanks.actions.changers.Changer;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ExceptionContext {
    Exception exception;
    Action<Changer<State>> command;
}
