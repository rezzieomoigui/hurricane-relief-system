package com.model;
 
public class HurricaneReliefApplication {

    private User currentUser;

    public void createAccount(String firstName, String lastName, String username, String password,
    String email, String dateOfBirth, String address, String zipCode, String language, boolean isAdmin) {
        UserList userList = UserList.getInstance();
        userList.addUser(firstName, lastName, username, password, email, dateOfBirth, address, zipCode, language, isAdmin);
    }

    public String login(String username, String password) {
        UserList userList = UserList.getInstance();
        User user = userList.getUser(username);
        if (user != null) {
            this.currentUser = user;
            return user.login(username, password);
        } else {
            return "User not found.";
        }
    }
    public void logout(String username) {
        UserList userList = UserList.getInstance();
        User user = userList.getUser(username);
        if (user != null) {
            user.logout();
        }
    }
} 
 