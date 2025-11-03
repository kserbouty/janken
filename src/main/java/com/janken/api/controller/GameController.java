package com.janken.api.controller;

import com.janken.api.model.Game;
import com.janken.api.repository.GameRepository;

import com.janken.api.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Random;

@Controller
public class GameController {

    private final GameRepository gameRepository;
    private final GameService gameService;

    public GameController(GameService gameService, GameRepository gameRepository) {
        this.gameService = gameService;
        this.gameRepository = gameRepository;
    }

    @GetMapping("/result")
    public String playGame(
            @RequestParam(name="choice") String playerChoice,
            Model model) throws Exception
    {
        try {
            Game game = gameRepository.findScores();
            int cpuTarget = new Random().nextInt(3);
            String[] cpuChoices = {"Rock", "Paper", "Scissors"};

            String cpuChoice = cpuChoices[cpuTarget];
            String playerResult = null;

            boolean result;

            result = gameService.checkWin(playerChoice, cpuChoice);

            if (result) {
                int currentWins = game.getWins();
                game.setWins(currentWins + 1);
                playerResult = "Win";
            }

            result = gameService.checkLoss(playerChoice, cpuChoice);

            if (result){
                int currentLosses = game.getLosses();
                game.setLosses(currentLosses + 1);
                playerResult = "Loss";
            }

            result = gameService.checkDraw(playerChoice, cpuChoice);

            if (result){
                int currentDraws = game.getDraws();
                game.setDraws(currentDraws + 1);
                playerResult = "Draw";
            }

            gameRepository.save(game);

            playerChoice = playerChoice.toUpperCase().charAt(0) + playerChoice.substring(1);

            model.addAttribute("playerChoice", playerChoice);
            model.addAttribute("cpuChoice", cpuChoice);

            if (playerResult == null) {
                return "error";
            } else {
                model.addAttribute("playerResult", playerResult);
            }

            return "result";

        } catch (Exception e) {
            System.err.println(LocalDateTime.now() + " " + e.getMessage());
            throw e;
        }
    }
}