package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * UserList class contains methods for user
 * @author Fantastic Four
 */
public class UserList {
    private static UserList userList;
    private ArrayList<User> users;

    /**
     * UserList Constructor adds new users
     */
    private UserList() {
        users = new ArrayList<User>();
        users.add(new User("Petra", "Robinson", "probinson", "goodPassword5",
        "probinson@gmail.com", "08-19-2000", "10 Branch Road", "29218", "Spanish", false));
        users.add(new User("Mark", "Johnson", "mjohnson", "wowPassword", 
        "mjohnson.gmail.com", "12-12-02", "14 Stream Dr", "29211", "English", false));
    }

    /**
     * getInstance returns instance of the userList
     * @return instance of UserList
     */
    public static UserList getInstance() {
        if (userList == null) {
            userList = new UserList();
        }
        return userList;
    }

    /**
     * getUser finds user the user in userList based off of their username
     * @param username user username
     * @return user based off of username
     */
    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * getUser method finds user in userList based of of UUID
     * @param uuid user id
     * @return user based off of uuid
     */
    public User getUser(UUID uuid) {
        for (User user : users) {
            if (user.getUuid().equals(uuid)) {
                return user;
            }
        }
        return null;
    }

    /**
     * getUsers returns users
     * @return ArrayList of users
     */
    public ArrayList<User> getUsers() {
        return this.users;
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
        if (getUser(username) != null) {
            return false;
        }
        users.add(new User(firstName, lastName, username, password, email, 
                dateOfBirth, address, zipCode, language, isAdmin));
        return true;
    }

    /**
     * save method saves the userList
     * @return boolean true or false depending on if it saved properly
     */
    public boolean save() {
        // add this, not sure yet
    }
}