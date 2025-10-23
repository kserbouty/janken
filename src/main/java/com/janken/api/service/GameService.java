package com.janken.api.service;

import org.springframework.stereotype.Service;

@Service
public class GameService {

    public boolean winConditions(String cpuChoice, String playerChoice) {

        if (cpuChoice.equalsIgnoreCase("rock")
                && playerChoice.equalsIgnoreCase("paper")) {
            return true;
        }

        if (cpuChoice.equalsIgnoreCase("paper")
                && playerChoice.equalsIgnoreCase("scissors")) {
            return true;
        }

        return cpuChoice.equalsIgnoreCase("scissors")
                && playerChoice.equalsIgnoreCase("rock");
    }

    public boolean lossConditions(String cpuChoice, String playerChoice) {

        if (cpuChoice.equalsIgnoreCase("paper")
                && playerChoice.equalsIgnoreCase("rock")) {
            return true;
        }

        if (cpuChoice.equalsIgnoreCase("scissors")
                && playerChoice.equalsIgnoreCase("paper")) {
            return true;
        }

        return cpuChoice.equalsIgnoreCase("rock")
                && playerChoice.equalsIgnoreCase("scissors");
    }

    public boolean drawConditions(String cpuChoice, String playerChoice) {

        return cpuChoice.equalsIgnoreCase(playerChoice);
    }
}