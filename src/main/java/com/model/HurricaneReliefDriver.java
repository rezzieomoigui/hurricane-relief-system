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

        boolean created_account = application.createAccount(
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

        System.out.println("Created account: "+created_account);
    }

   
    public void scenario2() {
        System.out.println();
        System.out.println("Scenario 2: Log in and log out");

        boolean loginResult = application.login("asmith", "12345");
        System.out.println("Logging in Amy Smith: "+loginResult);
        boolean logout = application.logout();
        System.out.println("Logging out: "+logout);
        System.out.println(application.getCurrentUser());

        boolean loginResult2 = application.login("probinson", "goodPassword5");
        System.out.println("Logging in Petra Robinson: "+loginResult2);
        boolean logout2 = application.logout();
        System.out.println("Logging out: "+logout2);
        System.out.println(application.getCurrentUser());

    }


    public void scenario3() {
        System.out.println();
        System.out.println("Scenario 3: ");
    }
  
    public static void main(String[] args) {
        HurricaneReliefDriver driver = new HurricaneReliefDriver();
        driver.run();
    }
}