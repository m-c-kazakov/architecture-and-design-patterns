package com.otus.solid.first.war.of.tanks.interpreter;

import com.otus.solid.first.war.of.tanks.iocResolvers.IoC;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InterpreterTest {

    @Mock
    AnyCommand anyCommand;

    @Test
    void execute() {
        Function<Map<String, Object>, OrderParser> initFuncOrderParser = (map) -> new OrderParser((Map<String, Object>) map.get("varArgs"), (String) map.get("order"));
        IoC.resolve(Map.of("dependencyName", "OrderParser", "initFunc", initFuncOrderParser, "scopeId", "InterpreterTest"));

//        Function<Map<String, Object>, AnyCommand> initFuncAnyCommand = (map) -> new AnyCommand((String) map.get("needField1"), (Integer) map.get("needField2"));
        Function<Map<String, Object>, AnyCommand> initFuncAnyCommand = (map) -> anyCommand;
        IoC.resolve(Map.of("dependencyName", "AnyCommand", "initFunc", initFuncAnyCommand, "scopeId", "InterpreterTest"));


        Interpreter interpreter = new Interpreter("InterpreterTest","   {\n" +
                "            \"scopeId\": \"InterpreterTest\",\n" +
                "            \"dependencyName\": \"AnyCommand\",\n" +
                "            \"needField1\": \"needField1\",\n" +
                "            \"needField2\": 2\n" +
                "        }");

        interpreter.execute();


        verify(anyCommand, times(1)).execute();
    }
}