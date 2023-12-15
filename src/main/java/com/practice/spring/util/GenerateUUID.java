package com.practice.spring.util;

import java.util.UUID;

public class GenerateUUID {
    public String generateRandomID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
