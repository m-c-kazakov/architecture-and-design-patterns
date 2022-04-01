package com.otus.solid.first.war.of.tanks.interpreter;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.iocResolvers.IoC;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Interpreter implements Action {
    String scopeId;
    String order;

    @Override
    public void execute() {
        HashMap<String, Object> varArgs = new HashMap<>();
        Action orderParser = IoC.resolve(Map.of("dependencyName", "OrderParser", "varArgs", varArgs, "order", order, "scopeId", scopeId));
        orderParser.execute();
        Action action = IoC.resolve(varArgs);
        action.execute();
    }


}
