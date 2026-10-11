package com.model;

/**
 * HurricaneReliefApplication contains methods for the driver class
 * @author Fantastic Four
 */
public class HurricaneReliefApplication {

    private User currentUser;

    /**
     * createAccount method creates an account for the user
     * @param firstName user first name
     * @param lastName user last name
     * @param username user username
     * @param password user password
     * @param email user email
     * @param dateOfBirth user birthday
     * @param address user address
     * @param zipCode user zip code
     * @param language user language
     * @param isAdmin checks if user is an administrator
     */
    public void createAccount(String firstName, String lastName, String username, String password,
    String email, String dateOfBirth, String address, String zipCode, String language, boolean isAdmin) {
        UserList userList = UserList.getInstance();
        userList.addUser(firstName, lastName, username, password, email, dateOfBirth, address, zipCode, language, isAdmin);
    }

    /**
     * login method logs user into their account
     * @param username user username
     * @param password user password
     * @return boolean based on if user was able to login to their account
     */
    public boolean login(String username, String password) {
        UserList userList = UserList.getInstance();
        User user = userList.getUser(username);
        if (user != null) {
            this.currentUser = user;
            return user.login(username, password);
        } else {
            return false;
        }
    }

    /**
     * logout method logs user out of their account
     * @param username user username
     */
    public void logout(String username) {
        UserList userList = UserList.getInstance();
        User user = userList.getUser(username);
        if (user != null) {
            user.logout();
        }
    }
} 
 