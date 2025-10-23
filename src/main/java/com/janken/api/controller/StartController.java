package com.janken.api.controller;

import com.janken.api.model.Game;
import com.janken.api.repository.GameRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class StartController {

    private final GameRepository gameRepository;

    public StartController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/")
    public String start(Model model) throws Exception {

        try {
            Game game = gameRepository.score();

            model.addAttribute("playerWins", game.getWins());
            model.addAttribute("playerDraws", game.getDraws());
            model.addAttribute("playerLosses", game.getLosses());

            return "start";

        } catch (Exception e) {
            System.err.println(LocalDateTime.now() + " " + e.getMessage());
            throw e;
        }
    }
}