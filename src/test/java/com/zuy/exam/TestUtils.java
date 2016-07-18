package com.zuy.exam;

import com.zuy.exam.entities.User;

public class TestUtils {
    public static User createUser(String login, String passwordHash, String role) {
        final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(passwordHash);
        user.setRole(role);
        return user;
    }
}
