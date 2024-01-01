package com.example.Practice.global.util;

import java.util.Random;

public class PasswordUtil {

    public static final int PASSWORD_SIZE = 8;

    public static String generateRandomPassword(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        StringBuilder password = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < PASSWORD_SIZE; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }
}
