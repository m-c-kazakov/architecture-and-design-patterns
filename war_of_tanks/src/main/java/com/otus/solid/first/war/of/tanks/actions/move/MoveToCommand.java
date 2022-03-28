package com.otus.solid.first.war.of.tanks.actions.move;

import com.otus.solid.first.war.of.tanks.actions.Action;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MoveToCommand implements Action {
    @Override
    public void execute() {
        log.info("Поведение не реализовано т.к. не обозначено ТЗ. Обработка команды MoveToCommand приводит к тому, что будет возвращена ссылка на состояние MoveTo");
    }
}
