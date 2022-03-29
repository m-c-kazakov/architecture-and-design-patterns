package com.otus.solid.first.war.of.tanks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otus.solid.first.war.of.tanks.actions.Action;
import io.jsonwebtoken.Jwts;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.keys.KeysParser;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class StartGameControllerTest {

    @Value("${otus.auth.private}")
    String privateKey;

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
    void start() {
        String jwt = Jwts.builder()
                .addClaims(Map.of(
                        "isCan", true,
                        "userId", "userId",
                        "battleId", "battleId"
                ))
                .signWith(KeysParser.getPrivateKeyFromString(privateKey))
                .compact();

        mockMvc.perform(post("/start")
                        .header("TOKEN", jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new Message("MessageControllerImplTest", "gameObjectId", "OperationId", new HashMap<>()))))
                .andExpect(status().isOk());
    }
}