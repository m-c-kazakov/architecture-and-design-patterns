package com.otus.solid.first.war.of.tanks.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Map;

/**
 * Сообщение, которое отправляет Агент игровому серверу
 * <p>
 * scopeId - для идентификации игры, в рамках которой это сообщение обработано. С помощью этого id можно будет определить получателя сообщения при маршрутизации сообщения внутри игрового сервера.
 * gameObjectId - id игрового объекта, которому адресовано сообщение. С помощью этого id можно будет определить игровой объект внутри игры, для которого адресовано это сообщение.
 * operationId - id операции - по этому id в IoC можно будет определить команду, которая будет обрабатывать данное сообщение.
 * varargs - вложенный json объект с параметрами операции. Содержимое этого объекта полностью зависит от команды, которая будет применяться к игровому объекту.
 */

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Message {
    String scopeId;
    String gameObjectId;
    String operationId;
    Map<String, Object> varargs;


    public Map<String, Object> getVarargsForIoC() {
        varargs.put("dependencyName", operationId);
        varargs.put("scopeId", scopeId);
        varargs.put("gameObjectId", gameObjectId);
        return varargs;
    }
}
