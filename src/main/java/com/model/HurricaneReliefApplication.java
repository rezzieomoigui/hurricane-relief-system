package com.model;
 
public class HurricaneReliefApplication {
    User currentUser;
    public void createAccount(String firstName, String lastName, String username, String password,
    String email, String dateOfBirth, String address, String zipCode, String language, boolean isAdmin) {
        UserList userList = UserList.getInstance();
        userList.addUser(firstName, lastName, username, password, email, dateOfBirth, address, zipCode, language, isAdmin);
    }

    public boolean login(String username, String password) {
        UserList userList = UserList.getInstance();
        User currentUser = userList.getUser(username);
        return currentUser != null;
    }
}
 
 