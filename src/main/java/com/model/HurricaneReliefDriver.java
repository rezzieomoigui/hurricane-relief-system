package com.model;
 
public class HurricaneReliefDriver {
    private HurricaneReliefApplication application;

 
    public HurricaneReliefDriver() {
        application = new HurricaneReliefApplication();
    }

 
    public void run() {
        scenario1();
        scenario2();
    }
 
    public void scenario1() {
        System.out.println();
        System.out.println("Scenario 1: Create an account");

        application.createAccount(
            "Amy",
            "Smith",
            "asmith",
            "12345",
            "amy@example.com",
            "2000-05-15",
            "123 Example Street",
            "29201",
            "English",
            false
        );

        System.out.println("Account information submitted for Amy Smith.");
    }

   
    public void scenario2() {
        System.out.println();
        System.out.println("Scenario 2: Log in and log out");

        String loginResult = application.login("asmith", "12345");

        if (!"Successfully logged in!".equals(loginResult)) {
            System.out.println("Sorry, we couldn't log in.");
            System.out.println(loginResult);
            return;
        }

        System.out.println("Amy Smith is now logged in.");

        String logoutResult = application.logout();
        System.out.println(logoutResult);
    }

  
    public static void main(String[] args) {
        HurricaneReliefDriver driver = new HurricaneReliefDriver();
        driver.run();
    }
}