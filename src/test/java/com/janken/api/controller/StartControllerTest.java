package com.janken.api.controller;

import com.janken.api.model.Game;
import com.janken.api.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StartController.class)
class StartControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    GameRepository gameRepository;

    @Test
    void should_display_start_page() throws Exception {
        Mockito
                .when(gameRepository.findScores())
                .thenReturn(new Game(1, 0, 0, 0));

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/")
                                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }
}