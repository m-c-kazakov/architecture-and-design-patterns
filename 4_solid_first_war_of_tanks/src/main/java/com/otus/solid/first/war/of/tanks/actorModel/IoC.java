package com.otus.solid.first.war.of.tanks.actorModel;

import java.util.Map;

public interface IoC {
    <T> T resolve(Map<String, Object> varargs);
}
