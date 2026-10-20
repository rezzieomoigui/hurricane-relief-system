package com.model;

import java.util.ArrayList;

 
public class HurricaneReliefApplication {
    private User currentUser;

  /**
   * creates account of individual new user
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
    * Logs user out of account if a current user exists
    * @return string representing if the user was logged out properly
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
     * getCurrentUser gets the current user
     * @return current user
     */
    /*
    public User getCurrentUser() {
        return currentUser;
    }
    */

    /**
     * getUser gets the user that matches the input username
     * @param username user username
     * @return a user with matching username as parameter
     */
    public User getUser(String username) {
        UserList userList = UserList.getInstance();
        return userList.getUser(username);
    }

    /**
     * getUsers returns users
     * @return ArrayList of users
     */
    public ArrayList<User> getUsers() {
        UserList userList = UserList.getInstance();
        return userList.getUsers();
    }

    /**
     * addUser method adds a user
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
     * @return boolean true if user was added and didn't already exist
     */
    public boolean addUser(String firstName, String lastName, String username, String password,
    String email, String dateOfBirth, String address, String zipCode, String language, boolean isAdmin) {
        UserList userList = UserList.getInstance();
        return userList.addUser(firstName, lastName, username, password, email, 
                dateOfBirth, address, zipCode, language, isAdmin);
    }
}