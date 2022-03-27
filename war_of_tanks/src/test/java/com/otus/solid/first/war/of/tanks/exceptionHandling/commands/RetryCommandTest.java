package com.otus.solid.first.war.of.tanks.exceptionHandling.commands;

import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.exceptionHandling.context.ExceptionContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RetryCommandTest {

    @Mock
    private Action action;

    @Test
    void execute() {
        doNothing().when(action).execute();

        ExceptionContext exceptionContext = ExceptionContext.builder().command(action).build();
        RetryCommand retryCommand = new RetryCommand(exceptionContext);
        retryCommand.execute();

        verify(action, times(1)).execute();


    }
}