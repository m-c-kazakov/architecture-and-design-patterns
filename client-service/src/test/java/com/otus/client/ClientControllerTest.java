package com.otus.client;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ClientControllerTest {

    @MockBean
    private TankBattleFeign tankBattleFeign;
    @MockBean
    private StartGameFeign startGameFeign;

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
    void registerAndSend() {
        mockMvc.perform(post("/registerAndSend")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new ClientDto(Set.of("123")))))
                .andExpect(status().isOk());

        verify(tankBattleFeign, timeout(1)).receiveMembers(anySet());
        verify(tankBattleFeign, timeout(1)).getJwtByUserIdAndBattleId(any());
        verify(startGameFeign, timeout(1)).start(any(), any());

    }
}