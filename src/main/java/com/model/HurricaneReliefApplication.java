package com.model;

import java.util.UUID;

 
public class HurricaneReliefApplication {
    private User currentUser;

  
    public boolean createAccount(String firstName, String lastName,
            String username, String password, String email,
            String dateOfBirth, String address, String zipCode,
            String language, boolean isAdmin) {

        UserList userList = UserList.getInstance();

        return userList.addUser(firstName, lastName, username, password,
                email, dateOfBirth, address, zipCode, language, isAdmin);
    }

    /**
     * Logs in and remembers the user if the credentials match.
     * @param username the user's username
     * @param password the user's password
     * @return the login result
     */
    public boolean login(String username, String password) {
        UserList userList = UserList.getInstance();
        User user = userList.getUser(username);

        if (user == null) {
            return false;
        }

        if (user.login(username, password))
            currentUser = user;

        return user.login(username, password);
    }

   
    public boolean logout() {
        currentUser = null;
        return true;
    }

  
    public User getCurrentUser() {
        return currentUser;
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