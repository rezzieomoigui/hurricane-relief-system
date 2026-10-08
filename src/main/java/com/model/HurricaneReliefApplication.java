package com.model;
 
public class HurricaneReliefApplication {

    public void createAccount(String firstName, String lastName, String username, String password,
    String email, String dateOfBirth, String address, String zipCode, String language, boolean isAdmin) {
        UserList userList = UserList.getInstance();
        userList.addUser(firstName, lastName, username, password, email, dateOfBirth, address, zipCode, language, isAdmin);
    }

    public String login(String username, String password) {
        UserList userList = UserList.getInstance();
<<<<<<< HEAD
        User user = userList.getUser(username); 
        return user.login(username, password);
=======
        User user = userList.getUser(username);
        if (user != null) {
            return user.login(username, password);
        } else {
            return "User not found.";
        }
>>>>>>> 2a079ee2e97bf14048220be201addceb3f56ea4b
    }
} 
 