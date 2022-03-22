package com.otus.solid.first.war.of.tanks.iocResolvers;

import com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions.ResolveException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Slf4j
public class IoC {

    private static final ThreadLocal<HashMap<String, Object>> threadLocal = ThreadLocal.withInitial(HashMap::new);

    /**
     * Метод инициализации зависемостей
     *
     * Может сохранять способ инициализации зависемостей в defaultScope или другом scope
     * ключ scopeId(String) - Если не передан, работа будет вестись в defaultScope
     *
     * ключ dependencyName(String) - название зависимости, которую нужно инициализировать и вернуть
     *
     * ключ initFunc(Function<Map<String, Object>) - функциональный интерфейс, которому известно как инициализировать объект
     *
     * Сначала идет попытка получить initFunc из контекста scope, если нет, то идет попытка получить их аргументов метода
     *
     * threadContextAction - Действие которое необходимо совершить с контекстом потока
     *
     *
     * @param varargs - аргументы метода
     * @param <T>
     * @return
     */
    public static  <T> T resolve(Map<String, Object> varargs) {

        try {

            HashMap<String, Object> threadContext = threadLocal.get();
            String scopeId = (String) varargs.getOrDefault("scopeId", "defaultScope");
            Map<String, Object> scopeMap = (Map<String, Object>) threadContext.getOrDefault(scopeId, new HashMap<>());

            Function<Map<String, Object>, T> initFunc = (Function<Map<String, Object>, T>) scopeMap.getOrDefault((String) varargs.get("dependencyName"), varargs.get("initFunc"));
            Assert.notNull(initFunc, "Не найдена функция инициализации зависимости");

            scopeMap.put((String) varargs.get("dependencyName"), initFunc);

            Optional.ofNullable(varargs.get("threadContextAction"))
                    .map(action -> (BiConsumer<HashMap<String, Object>, Map<String, Object>>) action )
                    .ifPresentOrElse(biConsumer -> biConsumer.accept(threadContext, varargs), () -> threadContext.put(scopeId, scopeMap));

            return initFunc.apply(varargs);
        } catch (Exception exception) {
            log.error("Ошибка при получении зависимости: " + exception.getMessage(), exception);
            throw new ResolveException("Ошибка при получении зависимости", exception);
        }
    }
}
