package com.otus.solid.first.war.of.tanks.iocResolvers;

import com.otus.solid.first.war.of.tanks.actions.state.checkers.FuelCheckerCommand;
import com.otus.solid.first.war.of.tanks.exceptionHandling.exceptions.ResolveException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class IoCTest {

    @Test
    @DisplayName("Получение зависимости из дефолтного скоупа")
    void resolve$1() {
        Function<Map<String, Object>, FuelCheckerCommand> initFunc = (map) -> new FuelCheckerCommand();
        Object resolve = IoC.resolve(Map.of("dependencyName", "FuelCheckerCommand", "initFunc", initFunc));
        assertEquals(FuelCheckerCommand.class.getCanonicalName(), resolve.getClass().getCanonicalName());

        Object class_init_context = IoC.resolve(Map.of("dependencyName", "FuelCheckerCommand"));
        assertEquals(FuelCheckerCommand.class.getCanonicalName(), class_init_context.getClass().getCanonicalName());



    }

    @Test
    @DisplayName("Получение зависимости из scopeId==1")
    void resolve$2() {
        Function<Map<String, Object>, FuelCheckerCommand> initFunc = (map) -> new FuelCheckerCommand();
        Object resolve = IoC.resolve(Map.of("dependencyName", "FuelCheckerCommand", "initFunc", initFunc, "scopeId", "1"));
        assertEquals(FuelCheckerCommand.class.getCanonicalName(), resolve.getClass().getCanonicalName());

        Object class_init_context = IoC.resolve(Map.of("dependencyName", "FuelCheckerCommand", "scopeId", "1"));
        assertEquals(FuelCheckerCommand.class.getCanonicalName(), class_init_context.getClass().getCanonicalName());

    }

    @Test
    @DisplayName("Использование defaultScope в 2 потоках. В одном будет функция инициализации в другом нет")
    void resolve$3() {

        Thread thread1 = new Thread(() -> {
            Function<Map<String, Object>, FuelCheckerCommand> initFunc = (map) -> new FuelCheckerCommand();
            Object resolve = IoC.resolve(Map.of("dependencyName", "FuelCheckerCommand", "initFunc", initFunc, "scopeId", "1"));
            assertEquals(FuelCheckerCommand.class.getCanonicalName(), resolve.getClass().getCanonicalName());

            Object class_init_context = IoC.resolve(Map.of("dependencyName", "FuelCheckerCommand", "scopeId", "1"));
            assertEquals(FuelCheckerCommand.class.getCanonicalName(), class_init_context.getClass().getCanonicalName());
        });

        Thread thread2 = new Thread(() -> {
            assertThrows(ResolveException.class, () -> IoC.resolve(Map.of("dependencyName", "FuelCheckerCommand", "scopeId", "1")), "Не получено ожидаемое исключение");
        });


        thread1.start();
        thread2.start();

    }
}