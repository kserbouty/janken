package com.janken.api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private final Game game = new Game(0, 1, 2, 3);

    @Test
    void should_get_correct_values() {
        assertEquals(0, game.getId());
        assertEquals(1, game.getWins());
        assertEquals(2, game.getDraws());
        assertEquals(3, game.getLosses());
    }

    @Test
    void should_set_correct_values() {
        game.setWins(4);
        assertEquals(4, game.getWins());
        game.setDraws(5);
        assertEquals(5, game.getDraws());
        game.setLosses(6);
        assertEquals(6, game.getLosses());
    }
}