package com.otus.solid.first.war.of.tanks.iocResolvers;

import com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions.ResolveException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

@Slf4j
public class IoC {

    private static final Map<String, Object> context = new HashMap<>();

    /**
     * Метод инициализации зависемостей
     * <p>
     * Может сохранять способ инициализации зависемостей в defaultScope или другом scope
     * ключ scopeId(String) - Если не передан, работа будет вестись в defaultScope
     * <p>
     * ключ dependencyName(String) - название зависимости, которую нужно инициализировать и вернуть
     * <p>
     * ключ initFunc(Function<Map<String, Object>) - функциональный интерфейс, которому известно как инициализировать объект
     * <p>
     * Сначала идет попытка получить initFunc из контекста scope, если нет, то идет попытка получить их аргументов метода
     * <p>
     * isSingleton - true/false Определяет будет ли один экземпляр объекта в этом скоупе
     *
     * @param varargs - аргументы метода
     * @param <T>
     * @return
     */
    @SneakyThrows
    public static <T> T resolve(Map<String, Object> varargs) {

        try {

            String scopeId = (String) varargs.getOrDefault("scopeId", "defaultScope");
            Map<String, Object> scopeMap = (Map<String, Object>) context.getOrDefault(scopeId, new HashMap<>());
            Object dependencyName = varargs.get("dependencyName");
            String isSingletonContextKey = "isSingleton_" + dependencyName;
            context.put(scopeId, scopeMap);


            Boolean isSingleton = (Boolean) ofNullable(scopeMap.get(isSingletonContextKey)).orElseGet(() -> {
                Boolean result = (Boolean) varargs.getOrDefault("isSingleton", false);
                scopeMap.put(isSingletonContextKey, result);
                return result;
            });

            Function<Map<String, Object>, T> initFuncVarArgs = (Function<Map<String, Object>, T>) varargs.get("initFunc");
            String initFuncContextKey = "initFunc_" + dependencyName;
            Function<Map<String, Object>, T> initFuncContext = (Function<Map<String, Object>, T>) scopeMap.get(initFuncContextKey);
            T dependency = (T) scopeMap.get(dependencyName);


            if (isSingleton && nonNull(dependency)) {
                return dependency;
            } else {
                Function<Map<String, Object>, T> initFunc = ObjectUtils.firstNonNull(initFuncContext, initFuncVarArgs);
                if (isNull(initFuncContext) && nonNull(initFuncVarArgs)) {
                    scopeMap.put(initFuncContextKey, initFunc);
                }
                if (nonNull(initFunc)) {
                    T result = initFunc.apply(varargs);
                    if (isSingleton) {
                        scopeMap.put((String) dependencyName, result);
                    }
                    return result;
                } else {
                    throw new IllegalAccessException("Не найдена функция инициализации зависимости=" + dependencyName);
                }
            }
        } catch (Exception exception) {
            log.error("Ошибка при получении зависимости: " + exception.getMessage(), exception);
            throw new ResolveException("Ошибка при получении зависимости", exception);
        }
    }
}
