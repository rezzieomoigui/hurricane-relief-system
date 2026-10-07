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

    public User(UUID uuid, String username, String firstName, String lastName, String password) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
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

    public String login(String username, String password) {
        if(isMatch(username, password)) 
            return "Logged in!";
        return "Incorrect username or password.";
    }

    private boolean isMatch(String username, String password) {
        UserList userList = UserList.getInstance();
        User user = userList.getUser(username);
        if(user == null)
            return false;
        return user.getPassword().equals(password);
    }

    public void resetPassword(String passwordUpdate) {
        this.password = passwordUpdate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getLanguage() {
        return language;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

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
