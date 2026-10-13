package com.model;

/**
 * Demonstrates account creation and login for the hurricane relief system.
 * @author Fantastic Four
 */
public class HurricaneReliefDriver {
    private HurricaneReliefApplication application;

    /**
     * Creates the application used by the driver.
     */
    public HurricaneReliefDriver() {
        application = new HurricaneReliefApplication();
    }

    /**
     * Runs the sample scenarios.
     */
    public void run() {
        scenario1();
        scenario2();
    }

    /**
     * Demonstrates creating a hurricane relief account.
     */
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

    /**
     * Demonstrates logging into the account.
     */
    public void scenario2() {
        System.out.println();
        System.out.println("Scenario 2: Log in");

        String result = application.login("asmith", "12345");

        if ("User not found.".equals(result)) {
            System.out.println("Sorry, we couldn't find that user.");
            return;
        }

        System.out.println("Login result for Amy Smith: " + result);
    }

    /**
     * Starts the driver.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        HurricaneReliefDriver driver = new HurricaneReliefDriver();
        driver.run();
    }
} 