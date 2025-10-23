package com.janken.api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private final Game game = new Game(0, 1, 2, 3);

    @Test
    void shouldGetCorrectValues() {
        assertEquals(0, game.getId());
        assertEquals(1, game.getWins());
        assertEquals(2, game.getDraws());
        assertEquals(3, game.getLosses());
    }

    @Test
    void shouldSetCorrectValues() {
        game.setWins(4);
        game.setDraws(5);
        game.setLosses(6);

        assertEquals(4, game.getWins());
        assertEquals(5, game.getDraws());
        assertEquals(6, game.getLosses());
    }
}