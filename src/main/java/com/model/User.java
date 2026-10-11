package com.model;
import java.util.Scanner;
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

    /**
     * User constructor creates user
     * @param uuid user id number 
     * @param username user username
     * @param firstName user first name
     * @param lastName user last name
     * @param password user password
     */
    public User(UUID uuid, String username, String firstName, String lastName, String password) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    /**
     * User constructor creates user
     * @param uuid user id number 
     * @param username user username
     * @param firstName user first name
     * @param lastName user last name
     * @param password user password
     * @param email user email
     * @param dateOfBirth user birthday
     * @param address user address
     * @param zipCode user zip code
     * @param language user language
     * @param isAdmin checks if user is an administrator
     */
    public User(UUID uuid, String firstName, String lastName, String username, String password,
    String email, String dateOfBirth, String address, String zipCode, String language, boolean isAdmin) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.zipCode = zipCode;
        this.language = language;
        this.isAdmin = isAdmin;
    }

    /**
     * User constructor creates user 
     * @param username user username
     * @param firstName user first name
     * @param lastName user last name
     * @param password user password
     * @param email user email
     * @param dateOfBirth user birthday
     * @param address user address
     * @param zipCode user zip code
     * @param language user language
     * @param isAdmin checks if user is an administrator
     */
    public User(String firstName, String lastName, String username, String password,
    String email, String dateOfBirth, String address, String zipCode, String language, boolean isAdmin) {
        this.uuid = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.zipCode = zipCode;
        this.language = language;
        this.isAdmin = isAdmin;
    }

    /**public void createAccount(String firstName, String lastName, String username, String password,
    String email, String dateOfBirth, String address, String zipCode, String language, boolean isAdmin) {
        UserList userList = UserList.getInstance();
        userList.addUser(firstName, lastName, username, password, email, 
            dateOfBirth, address, zipCode, language, isAdmin);
    }*/

    /**
     * login method calls the isMatch method to check if the username and password match
     * @param username user username
     * @param password user password
     * @return boolean if username and password match
     */
    public boolean login(String username, String password) {
        return isMatch(username, password);
    }

    /**
     * logout method shows that the user logged out of their account
     * @param username user username
     * @return string "logged out"
     */
    public String logout(String username) {
        return "Logged out!";
    }

    /**
     * isMatch method checks if the username and password match
     * @param username user username
     * @param password user password
     * @return boolean based on whether or not username and password match
     */
    private boolean isMatch(String username, String password) {
        UserList userList = UserList.getInstance();
        User user = userList.getUser(username);
        if(user == null)
            return false;
        return user.getPassword().equals(password);
    }

    /**
     * resetPassword method changes the current password to a new password
     * @param passwordUpdate
     */
    public void resetPassword(String passwordUpdate) {
        this.password = passwordUpdate;
    }

    /**
     * getUuid method returns the UUID
     * @return user UUID
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * getFirstName returns the first name
     * @return user first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * getLastName returns the last name    
     * @return user last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * getUsername returns the username
     * @return user username
     */
    public String getUsername() {
        return username;
    }

    /**
     * getPassword returns the password
     * @return user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * getEmail returns the email
     * @return user email
     */
    public String getEmail() {
        return email;
    }

    /**
     * getDateOfBirth returns the birthday
     * @return user birthday
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * getAddress returns the address
     * @return user address
     */
    public String getAddress() {
        return address;
    }

    /**
     * getZipCode returns the zip code
     * @return user zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * getLanguage returns the language
     * @return user language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * isAdmin checks if the user is an administrator
     * @return boolean based on if the user is an administrator
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * setFirstName sets the user first name
     * @param firstName user first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * setLastName sets the user last name
     * @param lastName user last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * setUsername sets the username
     * @param username user username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * setPassword method sets the password
     * @param password user password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * setEmail method sets the email
     * @param email user email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * setDateOfBirth sets the birthday
     * @param dateOfBirth user birthday
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * setAddress sets the address
     * @param address user address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * setZipCode sets the zip code
     * @param zipCode user zip code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * setLanguage method sets the language
     * @param language user language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * setAdmin method sets the boolean administrator
     * @param isAdmin checks if user is an administrator
     */
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * main scans in the information and tests creating an account
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("----- Creating An Account -----");
        System.out.println("Enter your first name: ");
        String firstName = keyboard.nextLine();
        System.out.println("Enter your last name: ");
        String lastName = keyboard.nextLine();
        System.out.println("Enter your username: ");
        String username = keyboard.nextLine();

    }
}
