package com.example.telegrambot;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthorizationService {
    private final List<Long> allowedUsers = Arrays.asList(123456789L, 987654321L); // IDs дозволених користувачів

    public boolean isAuthorized(Long userId) {
        return allowedUsers.contains(userId);
    }
}
