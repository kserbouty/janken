package com.janken.api.model;

public class Game {

    private final int id;
    private int wins;
    private int draws;
    private int losses;

    public Game(int id, int wins, int draws, int losses) {
        this.id = id;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
    }

    public int getId() {
        return id;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }
}