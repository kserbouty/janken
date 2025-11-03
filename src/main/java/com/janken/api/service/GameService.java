package com.janken.api.service;

import com.janken.api.model.Game;
import com.janken.api.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Random;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final Random random = new Random();
    private static final String ROCK = "rock";
    private static final String PAPER = "paper";
    private static final String SCISSORS = "scissors";

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public String getCpuChoice() {
        String[] choices = {"Rock", "Paper", "Scissors"};
        int target = this.random.nextInt(3);
        return choices[target];
    }

    public String getResult(String playerChoice, String cpuChoice) throws SQLException {

        Game game = gameRepository.findScores();

        if (this.checkWin(playerChoice, cpuChoice)) {
            int currentWins = game.getWins();
            game.setWins(currentWins + 1);
            gameRepository.save(game);
            return "Win";
        }

        if (this.checkLoss(playerChoice, cpuChoice)){
            int currentLosses = game.getLosses();
            game.setLosses(currentLosses + 1);
            gameRepository.save(game);
            return "Loss";
        }

        if (this.checkDraw(playerChoice, cpuChoice)){
            int currentDraws = game.getDraws();
            game.setDraws(currentDraws + 1);
            gameRepository.save(game);
            return "Draw";
        }

        return null;
    }

    private boolean checkWin(String playerChoice, String cpuChoice) {

        if (cpuChoice.equalsIgnoreCase(ROCK)
                && playerChoice.equalsIgnoreCase(PAPER)) {
            return true;
        }

        if (cpuChoice.equalsIgnoreCase(PAPER)
                && playerChoice.equalsIgnoreCase(SCISSORS)) {
            return true;
        }

        return cpuChoice.equalsIgnoreCase(SCISSORS)
                && playerChoice.equalsIgnoreCase(ROCK);
    }

    private boolean checkLoss(String playerChoice, String cpuChoice) {

        if (cpuChoice.equalsIgnoreCase(PAPER)
                && playerChoice.equalsIgnoreCase(ROCK)) {
            return true;
        }

        if (cpuChoice.equalsIgnoreCase(SCISSORS)
                && playerChoice.equalsIgnoreCase(PAPER)) {
            return true;
        }

        return cpuChoice.equalsIgnoreCase(ROCK)
                && playerChoice.equalsIgnoreCase(SCISSORS);
    }

    private boolean checkDraw(String playerChoice, String cpuChoice) {
        return cpuChoice.equalsIgnoreCase(playerChoice);
    }
}