package com.otus.solid.first.war.of.tanks.interpreter;

import com.otus.solid.first.war.of.tanks.actions.Action;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnyCommand implements Action {

    String needField1;
    Integer needField2;

    @Override
    public void execute() {

        log.info("Команда выполнена с данными needField1={}, needField2={}", needField1, needField2);

    }
}
