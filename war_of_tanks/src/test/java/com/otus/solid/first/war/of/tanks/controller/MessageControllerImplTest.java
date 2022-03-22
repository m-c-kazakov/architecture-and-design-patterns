package com.otus.solid.first.war.of.tanks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otus.solid.first.war.of.tanks.actions.Action;
import com.otus.solid.first.war.of.tanks.iocResolvers.IoC;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerImplTest {

    @Mock
    Action action;
    @Autowired
    private MockMvc mockMvc;

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @SneakyThrows
    void executeMessage() {
        Function<Map<String, Object>, Action> initFuncAction = (map) -> action;
        IoC.resolve(Map.of("dependencyName", "InterpretCommand", "initFunc", initFuncAction, "scopeId", "MessageControllerImplTest"));

        mockMvc.perform(post("/executeMessage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new Message("MessageControllerImplTest", "gameObjectId", "OperationId", new HashMap<>()))))
                .andExpect(status().isOk());

        verify(action, timeout(1)).execute();
    }
}