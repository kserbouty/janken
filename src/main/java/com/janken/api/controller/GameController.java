package com.janken.api.controller;

import com.janken.api.model.Game;
import com.janken.api.repository.GameRepository;

import com.janken.api.service.GameService;
import com.janken.api.service.UtilityService;
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
    private final UtilityService utilityService;

    public GameController(GameService gameService, GameRepository gameRepository, UtilityService utilityService) {
        this.gameService = gameService;
        this.gameRepository = gameRepository;
        this.utilityService = utilityService;
    }

    @GetMapping("/result")
    public String playGame(
            @RequestParam(name="choice") String playerChoice,
            Model model) throws Exception
    {
        try {
            Game game = gameRepository.game();
            int cpuTarget = new Random().nextInt(3);
            String[] cpuChoices = {"Rock", "Paper", "Scissors"};

            String cpuChoice = cpuChoices[cpuTarget];
            String playerResult = null;

            boolean result;

            result = gameService.checkWin(cpuChoice, playerChoice);

            if (result) {
                int currentWins = game.getWins();
                game.setWins(currentWins + 1);
                playerResult = "Win";
            }

            result = gameService.checkLoss(cpuChoice, playerChoice);

            if (result){
                int currentLosses = game.getLosses();
                game.setLosses(currentLosses + 1);
                playerResult = "Loss";
            }

            result = gameService.checkDraw(cpuChoice, playerChoice);

            if (result){
                int currentDraws = game.getDraws();
                game.setDraws(currentDraws + 1);
                playerResult = "Draw";
            }

            gameRepository.save(game);

            playerChoice = utilityService.format(playerChoice);

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