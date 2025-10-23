package com.janken.api.service;

import org.springframework.stereotype.Service;

@Service
public class UtilityService {

    public String format(String text) {
        return text.toUpperCase().charAt(0) + text.substring(1);
    }
}