package com.janken.api.controller;

import com.janken.api.model.Game;
import com.janken.api.repository.GameRepository;

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
    private final UtilityService utilityService;

    public GameController(GameRepository gameRepository, UtilityService utilityService) {
        this.gameRepository = gameRepository;
        this.utilityService = utilityService;
    }

    @GetMapping("/result")
    public String play(
            @RequestParam(name="choice") String playerChoice,
            Model model) throws Exception
    {
        String[] cpuChoices = {"rock", "paper", "scissors"};
        int cpuTarget = new Random().nextInt(3);
        String cpuChoice = cpuChoices[cpuTarget];

        String playerResult = "";

        try {

            Game game = gameRepository.score();

            if (cpuChoice.equalsIgnoreCase("rock")
                    && playerChoice.equalsIgnoreCase("paper")) {

                int currentWins = game.getWins();
                game.setWins(currentWins + 1);

                playerResult = "win";
            }
            if (cpuChoice.equalsIgnoreCase("paper")
                    && playerChoice.equalsIgnoreCase("scissors")) {

                int currentWins = game.getWins();
                game.setWins(currentWins + 1);

                playerResult = "win";
            }
            if (cpuChoice.equalsIgnoreCase("scissors")
                    && playerChoice.equalsIgnoreCase("rock")) {

                int currentWins = game.getWins();
                game.setWins(currentWins + 1);

                playerResult = "win";
            }

            if (cpuChoice.equalsIgnoreCase("paper")
                    && playerChoice.equalsIgnoreCase("rock")) {

                int currentLosses = game.getLosses();
                game.setLosses(currentLosses + 1);

                playerResult = "loss";
            }
            if (cpuChoice.equalsIgnoreCase("scissors")
                    && playerChoice.equalsIgnoreCase("paper")) {

                int currentLosses = game.getLosses();
                game.setLosses(currentLosses + 1);

                playerResult = "loss";
            }
            if (cpuChoice.equalsIgnoreCase("rock")
                    && playerChoice.equalsIgnoreCase("scissors")) {

                int currentLosses = game.getLosses();
                game.setLosses(currentLosses + 1);

                playerResult = "loss";
            }

            if (cpuChoice.equalsIgnoreCase(playerChoice)) {
                int currentDraws = game.getDraws();
                game.setDraws(currentDraws + 1);

                playerResult = "draw";
            }

            gameRepository.save(game);

            playerChoice = utilityService.format(playerChoice);
            cpuChoice = utilityService.format(cpuChoice);
            playerResult = utilityService.format(playerResult);

            model.addAttribute("playerChoice", playerChoice);
            model.addAttribute("cpuChoice", cpuChoice);
            model.addAttribute("playerResult", playerResult);

        } catch (Exception e) {
            System.err.println(LocalDateTime.now() + " " + e.getMessage());
            throw e;
        }

        return "result";
    }
}