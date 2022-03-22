package com.otus.solid.first.war.of.tanks.actorModel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailBoxStarterTest {

    @Mock
    private IoC ioC;
    @Mock
    private MailBoxExecutor mailBoxExecutor;

    @Test
    @DisplayName("Написать тест, который проверяет, что после команды старт поток запущен")
    void execute$1() {
        doReturn(mailBoxExecutor).when(ioC).resolve(anyMap());
        MailBoxStarter mailBoxStarter = new MailBoxStarter(ioC);
        mailBoxStarter.execute();
        verify(mailBoxExecutor, timeout(100).times(1)).execute();
    }


}