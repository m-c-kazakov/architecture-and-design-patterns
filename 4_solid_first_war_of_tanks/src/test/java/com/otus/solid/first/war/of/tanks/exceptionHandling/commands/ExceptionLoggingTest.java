package com.otus.solid.first.war.of.tanks.exceptionHandling.commands;

import com.otus.solid.first.war.of.tanks.exceptionHandling.context.ExceptionContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExceptionLoggingTest {
    @Mock
    Exception exception;



    @Test
    void execute() {
        when(exception.getMessage()).thenReturn("Ошибка для теста");
        ExceptionLogging exceptionLogging = new ExceptionLogging(ExceptionContext.builder().exception(exception).build());
        exceptionLogging.execute();
        verify(exception, times(1)).getMessage();


    }
}