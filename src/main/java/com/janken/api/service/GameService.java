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

        if (cpuChoice.equalsIgnoreCase("scissors")
                && playerChoice.equalsIgnoreCase("rock")) {
            return true;
        }

        return false;
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

        if (cpuChoice.equalsIgnoreCase("rock")
                && playerChoice.equalsIgnoreCase("scissors")) {
            return true;
        }

        return false;
    }

    public boolean drawConditions(String cpuChoice, String playerChoice) {

        if (cpuChoice.equalsIgnoreCase(playerChoice)) {
            return true;
        }

        return false;
    }
}