package com.janken.api.controller;

import com.janken.api.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/result")
    public String showResult(
            @RequestParam(name="choice") String playerChoice, Model model)
            throws SQLException
    {
        String cpuChoice = gameService.getCpuChoice();
        String playerResult = gameService.getResult(playerChoice, cpuChoice);

        if (playerResult == null) {
            return "error";
        } else {
            model.addAttribute("playerResult", playerResult);
        }
        playerChoice = playerChoice.toUpperCase().charAt(0) + playerChoice.substring(1);
        model.addAttribute("playerChoice", playerChoice);
        model.addAttribute("cpuChoice", cpuChoice);

        return "result";
    }
}