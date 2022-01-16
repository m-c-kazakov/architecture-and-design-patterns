package com.otus.solid.first.war.of.tanks.actions.gameObjects;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.actions.State;
import com.otus.solid.first.war.of.tanks.actions.changers.Changer;
import com.otus.solid.first.war.of.tanks.exceptionHandling.BaseExceptionsHandler;

import java.util.Map;

/**
 * Игоровой объект
 * Содержит шаблоны действий, который он может совершить
 * И конкретные команды на изменения, которые могут быть совершены в рамках этих действий
 * Отвечает, за совершение действия и вызов обработчика ошибок, которые могут возникнуть в рамках действий
 */
public interface GameObject {
    void setActions(Map<String, Action<Changer<State>>> actions);
    void setChangers(Map<String, Changer<State>> changers);
    void setBaseExceptionsHandler(BaseExceptionsHandler handlers);

    void setActionsQueue(String actionName, String changerName);

    void execute();
}
