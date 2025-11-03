package com.janken.api.controller;

import com.janken.api.model.Game;
import com.janken.api.repository.GameRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

@Controller
public class StartController {

    private final GameRepository gameRepository;

    public StartController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/")
    public String showStart(Model model) throws SQLException {

        Game game = gameRepository.findScores();

        model.addAttribute("playerWins", game.getWins());
        model.addAttribute("playerDraws", game.getDraws());
        model.addAttribute("playerLosses", game.getLosses());

        return "start";
    }
}