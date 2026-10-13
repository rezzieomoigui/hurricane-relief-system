package com.model;

import java.util.UUID;

/**
 * HurricaneReliefApplication contains methods for the driver class
 * @author Fantastic Four
 */
public class HurricaneReliefApplication {

    public void createAccount(String firstName, String lastName, String username, String password,
    String email, String dateOfBirth, String address, String zipCode, String language, boolean isAdmin) {
        UserList userList = UserList.getInstance();
        userList.addUser(firstName, lastName, username, password, email, dateOfBirth, address, zipCode, language, isAdmin);
    }

    public String login(String username, String password) {
        UserList userList = UserList.getInstance();
        User user = userList.getUser(username);
        if (user != null) {
            return String.valueOf(user.login(username, password));
        } else {
            return "User not found.";
        }
    }
    public User getUser(String username) {
        UserList userList = UserList.getInstance();
        return userList.getUser(username);
    }
    public User getUser(UUID uuid) {
        UserList userList = UserList.getInstance();
        return userList.getUser(uuid);
    }
} 
 