package com.codecool.shop.controller;

import java.security.SecureRandom;
import java.util.Base64;

public class SecurityHandler {

    public static String createHashedPassword(String password, String salt) {
        byte[] bytes = (password + salt).getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String createSalt() {
        SecureRandom random = new SecureRandom();
        byte saltInBytes[] = new byte[32];
        random.nextBytes(saltInBytes);
        return Base64.getEncoder().encodeToString(saltInBytes);
    }
}
