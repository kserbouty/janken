package com.janken.api.controller;

import com.janken.api.service.GameService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
class GameControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    GameService gameService;

    @Test
    void should_display_result_page_when_rock_against_rock() throws Exception {
        Mockito.when(gameService.getCpuChoice()).thenReturn("Rock");

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/result")
                                .param("choice", "rock")
                                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }

    @Test
    void should_display_result_page_when_rock_against_paper() throws Exception {
        Mockito.when(gameService.getCpuChoice()).thenReturn("Paper");

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/result")
                                .param("choice", "rock")
                                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }

    @Test
    void should_display_result_page_when_rock_against_scissors() throws Exception {
        Mockito.when(gameService.getCpuChoice()).thenReturn("Scissors");

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/result")
                                .param("choice", "rock")
                                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }

    @Test
    void should_display_result_page_when_paper_against_rock() throws Exception {
        Mockito.when(gameService.getCpuChoice()).thenReturn("Rock");

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/result")
                                .param("choice", "paper")
                                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }

    @Test
    void should_display_result_page_when_paper_against_paper() throws Exception {
        Mockito.when(gameService.getCpuChoice()).thenReturn("Paper");

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/result")
                                .param("choice", "paper")
                                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }

    @Test
    void should_display_result_page_when_paper_against_scissors() throws Exception {
        Mockito.when(gameService.getCpuChoice()).thenReturn("Scissors");

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/result")
                                .param("choice", "paper")
                                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }

    @Test
    void should_display_result_page_when_scissors_against_rock() throws Exception {
        Mockito.when(gameService.getCpuChoice()).thenReturn("Rock");

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/result")
                                .param("choice", "scissors")
                                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }

    @Test
    void should_display_result_page_when_scissors_against_paper() throws Exception {
        Mockito.when(gameService.getCpuChoice()).thenReturn("Paper");

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/result")
                                .param("choice", "scissors")
                                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }

    @Test
    void should_display_result_page_when_scissors_against_scissors() throws Exception {
        Mockito.when(gameService.getCpuChoice()).thenReturn("Scissors");

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/result")
                                .param("choice", "scissors")
                                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }
}