package com.model;

public class UserList {
    private static UserList userList;
    private ArrayList<User> users;

    private UserList() {
        users = new ArrayList<>();
    }

    public static UserList getInstance() {
        if (userList == null) {
            userList = new UserList();
        }
        return userList;
    }

    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User getUser(UUID uuid) {
        for (User user : users) {
            if (user.getUuid().equals(uuid)) {
                return user;
            }
        }
        return null;
    }

    public boolean addUser(String firstName, String lastName, String username, String email) {
        if (getUser(username) == null) {
            users.add(new User(firstName, lastName, username, email));
            return true;
        }
        return false;
    }

    public boolean save() {
        // add this, not sure yet
    }
}