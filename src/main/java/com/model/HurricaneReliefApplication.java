package com.model;

import java.util.UUID;

/**
 * Contains methods for the hurricane relief driver.
 * @author Fantastic Four
 */
public class HurricaneReliefApplication {
    private User currentUser;

    /**
     * Creates an account using the provided information.
     */
    public void createAccount(String firstName, String lastName,
            String username, String password, String email,
            String dateOfBirth, String address, String zipCode,
            String language, boolean isAdmin) {

        UserList userList = UserList.getInstance();

        userList.addUser(firstName, lastName, username, password,
                email, dateOfBirth, address, zipCode, language, isAdmin);
    }

    /**
     * Logs in and remembers the user if the credentials match.
     * @param username the user's username
     * @param password the user's password
     * @return the login result
     */
    public String login(String username, String password) {
        UserList userList = UserList.getInstance();
        User user = userList.getUser(username);

        if (user == null) {
            return "User not found.";
        }

        if (!user.login(username, password)) {
            return "Incorrect password.";
        }

        currentUser = user;
        return "Successfully logged in!";
    }

    /**
     * Logs out and clears the current user.
     * @return the logout result
     */
    public String logout() {
        if (currentUser == null) {
            return "No user is currently logged in.";
        }

        String result = currentUser.logout(currentUser.getUsername());
        currentUser = null;

        return result;
    }

    /**
     * Returns the currently logged-in user.
     * @return the current user, or null if nobody is logged in
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Finds a user by username.
     * @param username the username to look up
     * @return the matching user
     */
    public User getUser(String username) {
        UserList userList = UserList.getInstance();
        return userList.getUser(username);
    }

    /**
     * Finds a user by UUID.
     * @param uuid the user ID to look up
     * @return the matching user
     */
    public User getUser(UUID uuid) {
        UserList userList = UserList.getInstance();
        return userList.getUser(uuid);
    }
}