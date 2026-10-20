package com.model;

import java.util.ArrayList;
 
public class HurricaneReliefDriver {
    private HurricaneReliefApplication application;

 
    public HurricaneReliefDriver() {
        application = new HurricaneReliefApplication();
    }

 
    public void run() {
        scenario1();
        scenario2();
        scenario3();
        scenario4();
        scenario5();
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


    /**
     * Scenario 3: a victim in Charleston finds nearby shelters, checks hurricane alerts and asks for help
     */
    public void scenario3() {
        System.out.println();
        System.out.println("Scenario 3: Find shelters, check hurricane alerts, and request relief");

        application.createAccount("Carl", "Martin", "cmartin", "pass123", "carl@example.com",
            "1990-03-02", "5 King Street", "29401", "English", false);

        if (!"Successfully logged in!".equals(application.login("cmartin", "pass123"))) {
            System.out.println("Sorry, we couldn't log in.");
            return;
        }

        System.out.println("Nearby shelters:");
        for (Shelter shelter : application.seeNearbyShelters()) {
            System.out.println("  " + shelter);
        }

        System.out.println("Hurricane alerts:");
        for (String alert : application.getHurricaneAlerts()) {
            System.out.println("  " + alert);
        }

        ReliefRequest request = application.submitReliefRequest("5 King Street");
        System.out.println("Relief request submitted (" + request.getStatus().str + "): " + request.getRequestId());

        System.out.println(application.logout());
    }

    /**
     * Scenario 4: a volunteer views the available requests and accepts one
     */
    public void scenario4() {
        System.out.println();
        System.out.println("Scenario 4: Volunteer views and accepts a relief request");

        if (!"Successfully logged in!".equals(application.login("mcollins", "securePassword5"))) {
            System.out.println("Sorry, we couldn't log in.");
            return;
        }

        ArrayList<ReliefRequest> available = application.getAvailableRequests();
        System.out.println("Available requests: " + available.size());
        for (ReliefRequest request : available) {
            System.out.println("  " + request.getRequestId() + " at " + request.getAddress());
        }

        // accept the request that Carl made in scenario 3
        for (ReliefRequest request : available) {
            if ("5 King Street".equals(request.getAddress())) {
                ReliefRequest details = application.viewReliefRequest(request.getUuid());
                System.out.println("Viewing request from " + details.getAddress() + " (" + details.getStatus().str + ")");
                System.out.println(application.acceptRequest(request.getUuid()));
                System.out.println("Status is now: " + details.getStatus().str);
                System.out.println(application.markCompleteRequest(request.getUuid()));
                System.out.println("Status is now: " + details.getStatus().str);
            }
        }

        System.out.println(application.logout());
    }

    /**
     * Scenario 5: Carl removes his demo request so running the driver again starts clean
     */
    public void scenario5() {
        System.out.println();
        System.out.println("Scenario 5: Cancel a relief request");

        if (!"Successfully logged in!".equals(application.login("cmartin", "pass123"))) {
            System.out.println("Sorry, we couldn't log in.");
            return;
        }

        for (ReliefRequest request : application.getMyReliefRequests()) {
            System.out.println("Cancelling request " + request.getRequestId() + ": " + application.cancelReliefRequest(request.getUuid()));
        }

        System.out.println(application.logout());
    }

    public static void main(String[] args) {
        HurricaneReliefDriver driver = new HurricaneReliefDriver();
        driver.run();
    }
}