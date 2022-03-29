package com.otus.authorization.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otus.authorization.dto.ClientDto;
import com.otus.authorization.dto.UserDataDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.AssertJUnit.assertNotNull;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TankBattleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void receiveMembers() {
        MvcResult mvcResult = mockMvc.perform(post("/receiveMembers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new ClientDto(Set.of("userId")))))
                .andExpect(status().isOk())
                .andReturn();

        String battleId = mvcResult.getResponse().getContentAsString();
        assertNotNull(battleId);

    }

    @Test
    @SneakyThrows
    void getJwtByUserIdAndBattleId() {
        String userId = "userId";
        MvcResult mvcResult = mockMvc.perform(post("/receiveMembers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new ClientDto(Set.of(userId)))))
                .andExpect(status().isOk())
                .andReturn();

        String battleId = mvcResult.getResponse().getContentAsString();

        mockMvc.perform(post("/getJwtByUserIdAndBattleId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(UserDataDto
                                .builder()
                                .userId(userId)
                                .battleId(battleId)
                                .build())))
                .andExpect(status().isOk());
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

