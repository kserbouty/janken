package com.janken.api.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    private final GameService gameService = new GameService();

    @Test
    void checkWinConditions() {
        boolean win = gameService.checkWin("Rock", "Scissors");
        boolean draw = gameService.checkWin("Paper", "Rock");
        boolean loss = gameService.checkWin("Scissors", "Paper");
        assertTrue(win);
        assertTrue(draw);
        assertTrue(loss);
    }

    @Test
    void checkLossConditions() {
        boolean win = gameService.checkLoss("Rock", "Paper");
        boolean draw = gameService.checkLoss("Paper", "Scissors");
        boolean loss = gameService.checkLoss("Scissors", "Rock");
        assertTrue(win);
        assertTrue(draw);
        assertTrue(loss);
    }

    @Test
    void checkDrawConditions() {
        boolean rock = gameService.checkDraw("Rock", "Rock");
        boolean paper = gameService.checkDraw("Paper", "Paper");
        boolean scissors = gameService.checkDraw("Scissors", "Scissors");
        assertTrue(rock);
        assertTrue(paper);
        assertTrue(scissors);
    }
}