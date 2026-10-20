package com.model;

 
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

    /**
     * getCurrentUser gets the current user
     * @return current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * getUser gets the user that matches the input username
     * @param username user username
     * @return a user with matching username as parameter
     */
    public User getUser(String username) {
        UserList userList = UserList.getInstance();
        return userList.getUser(username);
    }
}