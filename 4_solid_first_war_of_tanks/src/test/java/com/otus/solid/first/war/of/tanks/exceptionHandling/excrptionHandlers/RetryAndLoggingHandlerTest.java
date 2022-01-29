package com.otus.solid.first.war.of.tanks.exceptionHandling.excrptionHandlers;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.exceptionHandling.context.ExceptionContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RetryAndLoggingHandlerTest {

    @Mock
    Action action;
    @Mock
    Exception exception;


    @Test
    void processing() {
        doThrow(new RuntimeException()).when(action).execute();
        ExceptionContext exceptionContext = ExceptionContext.builder().command(action).exception(exception).build();
        RetryAndLoggingHandler retryAndLoggingHandler = new RetryAndLoggingHandler();
        retryAndLoggingHandler.processing(exceptionContext);
        verify(action, times(1)).execute();
        verify(exception, times(1)).getMessage();

    }
}