package com.janken.api.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilityServiceTest {

    private final UtilityService utilityService = new UtilityService();

    @Test
    void shouldFormatStrings() {
        String unformatted = "player";
        String formatted = utilityService.format(unformatted);
        assertEquals("Player", formatted);
    }
}