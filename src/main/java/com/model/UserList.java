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
        ArrayList<User> loaded = DataLoader.getUsers();
        if (loaded != null && !loaded.isEmpty()) {
            this.users = loaded;
        }
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
    String email, String dateOfBirth, String address, String city, String state, String zipCode, String language, boolean isAdmin, UserType userType) {
        if (getUser(username) != null) {
            return false;
        }
        users.add(new User(firstName, lastName, username, password, email, 
                dateOfBirth, address, city, state, zipCode, language, isAdmin, userType));
        return true;
    }

    /**
     * addVolunteer adds a volunteer 
     * @param firstName volunteer first name
     * @param lastName volunteer last name
     * @param username volunteer username
     * @param password volunteer password
     * @param email volunteer email
     * @param dateOfBirth volunteer date of birth
     * @param address volunteer address
     * @param city volunteer city
     * @param state volunteer state
     * @param zipCode volunteer zip code
     * @param language volunteer language
     * @param isAdmin checkes if volunteer is an administrator
     * @param userType user type
     * @param skills volunteer skills
     * @param firstResponder checks if volunteer is a first responder
     * @param canSwim checks if the volunteer can swim
     * @param isShelterOwner checks if the volunteer is a shelter owner
     * @return boolean representing whether or not the volunteer was added
     */
    public boolean addVolunteer(String firstName, String lastName, String username, String password, String email, String dateOfBirth, String address, String city, String state, String zipCode, String language, boolean isAdmin, UserType userType, Skills skills, boolean firstResponder, boolean canSwim, boolean isShelterOwner) {
        if (getUser(username) != null)
            return false;
        users.add(new Volunteer(firstName, lastName, username, password, email, dateOfBirth, address, city, state, zipCode, language, isAdmin, userType, skills, firstResponder, canSwim, isShelterOwner));
        return true;
    }

    /**
     * save method saves the userList
     * @return boolean true or false depending on if it saved properly
     */
    public boolean save() {
        return DataWriter.saveUsers();
    }
}
