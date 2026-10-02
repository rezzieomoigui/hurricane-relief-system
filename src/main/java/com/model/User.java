package com.model;
import java.util.UUID;

public class User {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String dateOfBirth;
    private String address;
    private String zipCode;
    private String language;
    private boolean isAdmin;

    public User(UUID uuid, String firstName, String lastName, String username, String password,
    String email, String dateOfBirth, String address, String zipCode, String language, boolean isAdmin) {

    }

    public void createAccount(String username, String password) {
        
    }

    public boolean isMatch(String username, String password) {
        return true;
    }

    public void resetPassword(String passwordUpdate) {
        
    }

    

}
