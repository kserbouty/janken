package com.janken.api.controller;

import com.janken.api.model.Game;
import com.janken.api.repository.GameRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Random;

@Controller
public class GameController {

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/result")
    public String play(@RequestParam(name="choice") String playerChoice, Model model) throws Exception {

        String[] cpuChoices = {"rock", "paper", "scissors"};
        int cpuTarget = new Random().nextInt(3);
        String cpuChoice = cpuChoices[cpuTarget];

        String result = "";

        try {

            Game game = gameRepository.score();

            if (cpuChoice.equalsIgnoreCase("rock")
                    && playerChoice.equalsIgnoreCase("paper")) {

                int currentWins = game.getWins();
                game.setWins(currentWins + 1);

                result = "win";
            }
            if (cpuChoice.equalsIgnoreCase("paper")
                    && playerChoice.equalsIgnoreCase("scissors")) {

                int currentWins = game.getWins();
                game.setWins(currentWins + 1);

                result = "win";
            }
            if (cpuChoice.equalsIgnoreCase("scissors")
                    && playerChoice.equalsIgnoreCase("rock")) {

                int currentWins = game.getWins();
                game.setWins(currentWins + 1);

                result = "win";
            }

            if (cpuChoice.equalsIgnoreCase("paper")
                    && playerChoice.equalsIgnoreCase("rock")) {

                int currentLosses = game.getLosses();
                game.setLosses(currentLosses + 1);

                result = "loss";
            }
            if (cpuChoice.equalsIgnoreCase("scissors")
                    && playerChoice.equalsIgnoreCase("paper")) {

                int currentLosses = game.getLosses();
                game.setLosses(currentLosses + 1);

                result = "loss";
            }
            if (cpuChoice.equalsIgnoreCase("rock")
                    && playerChoice.equalsIgnoreCase("scissors")) {

                int currentLosses = game.getLosses();
                game.setLosses(currentLosses + 1);

                result = "loss";
            }

            if (cpuChoice.equalsIgnoreCase(playerChoice)) {
                int currentDraws = game.getDraws();
                game.setDraws(currentDraws + 1);

                result = "draw";
            }

            gameRepository.save(game);

            playerChoice = playerChoice.toUpperCase().charAt(0)
                    + playerChoice.substring(1);
            cpuChoice = cpuChoice.toUpperCase().charAt(0)
                    + cpuChoice.substring(1);
            String playerResult = result.toUpperCase().charAt(0)
                    + result.substring(1);

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