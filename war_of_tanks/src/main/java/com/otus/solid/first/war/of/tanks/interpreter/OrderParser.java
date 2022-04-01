package com.otus.solid.first.war.of.tanks.interpreter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions.IncorrectDataException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderParser implements Action {

    Map<String, Object> varArgs;
    String order;

    public void execute() {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> resultMap = mapper.readValue(order, HashMap.class);
            varArgs.putAll(resultMap);
        } catch (Exception e) {
            throw new IncorrectDataException("OrderParser", "Данные команды присланы в не правильном формате");
        }
    }
}
