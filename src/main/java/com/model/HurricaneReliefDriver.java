package com.model;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Console menu for the Hurricane Relief Application.
 */
public class HurricaneReliefDriver {

    private final HurricaneReliefApplication application;
    private final Scanner scanner;
    private final HashMap<String, String> emergencyContacts;

    /**
     * This is where the driver starts and creates the input tools.
     */
    public HurricaneReliefDriver() {
        // Public no-arguement constructor to create the HurricaneReliefDriver object.
        application = new HurricaneReliefApplication();
        scanner = new Scanner(System.in);
        emergencyContacts = new HashMap<>();
    }

    /**
     * The program starts here with the menu loop
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        HurricaneReliefDriver driver = new HurricaneReliefDriver();
        driver.run();
    }

    /**
     * Repeats the menu until the user leaves the application. Each menu option calls a method in the HurricaneReliefApplication class.
     */
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    createAccount();
                    break;

                case "2":
                    addEmergencyContact();
                    break;

                case "3":
                    application.resetPassword(
                            readText("Enter your new password: "));
                    break;

                case "4":
                    System.out.println(application.hurricaneIncoming());
                    System.out.println(application.hurricaneLeaving());
                    break;

                case "5":
                    System.out.println("Closed roads:");
                    System.out.println(application.closedRoads());

                    System.out.println("Safe roads:");
                    System.out.println(application.safeRoads());
                    break;

                case "6":
                    application.displayHurricaneTips();
                    break;

                case "7":
                    application.displayFloodingTips();
                    break;

                case "8":
                    application.displayFirstAidTips();
                    break;

                case "9":
                    application.displayAftermathGuidelines();
                    break;

                case "10":
                    application.addToChecklist();
                    break;

                case "11":
                    application.markAsComplete();
                    break;

                case "12":
                    application.markAsIncomplete();
                    break;

                case "13":
                    application.registerShelter();
                    break;

                case "14":
                    System.out.println("Nearby shelters:");
                    System.out.println(application.seeNearbyShelters());
                    break;

                case "15":
                    if (application.shelterFull()) {
                        System.out.println("The shelter is full.");
                    } else {
                        System.out.println("The shelter has space available.");
                    }
                    break;

                case "16":
                    application.reliefInquiry();
                    break;

                case "17":
                    if (emergencyContacts.isEmpty()) {
                        System.out.println(
                                "Please add an emergency contact first.");
                    } else {
                        System.out.println(
                                application.safeMessage(emergencyContacts));
                    }
                    break;

                case "18":
                    application.makeDonation();
                    break;

                case "19":
                    application.automaticDonation();
                    break;

                case "20":
                    application.technicalAssistance();
                    break;

                case "0":
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Please choose a number from the menu.");
            }
        }

        scanner.close();
    }

    /**
     * Displays the available actions.
     */
    private void displayMenu() {
        System.out.println("\n===== Hurricane Relief Application =====");
        System.out.println("1. Create an account");
        System.out.println("2. Add an emergency contact");
        System.out.println("3. Reset password");
        System.out.println("4. View hurricane updates");
        System.out.println("5. View closed and safe roads");
        System.out.println("6. View hurricane preparation tips");
        System.out.println("7. View flooding tips");
        System.out.println("8. View first aid tips");
        System.out.println("9. View aftermath guidelines");
        System.out.println("10. Add to preparation checklist");
        System.out.println("11. Mark checklist item complete");
        System.out.println("12. Mark checklist item incomplete");
        System.out.println("13. Register a shelter");
        System.out.println("14. View nearby shelters");
        System.out.println("15. Check shelter availability");
        System.out.println("16. Make a relief inquiry");
        System.out.println("17. Send a safe message");
        System.out.println("18. Make a donation");
        System.out.println("19. Set up automatic donations");
        System.out.println("20. Get technical assistance");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    /**
     * Collects account information and passes it to the application 
     */
    private void createAccount() {
        String userName = readText("Enter a username: ");
        String password = readText("Enter a password: ");

        application.createAccount(userName, password);
    }

    /**
     * Adds a contact and passes contacts to application to send a safe message
     */
    private void addEmergencyContact() {
        String name = readText("Enter the contact's name: ");
        String phoneNumber = readText("Enter the contact's phone number: ");

        emergencyContacts.put(name, phoneNumber);
        application.addEmergencyContacts(
                new HashMap<>(emergencyContacts));
    }

    /**
     * Reads a nonempty answer from the user and returns it. Keeps prompting until a nonempty answer is given.
     *
     * @param prompt question to display
     * @return the user's answer
     */
    private String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String answer = scanner.nextLine().trim();

            if (!answer.isEmpty()) {
                return answer;
            }

            System.out.println("Please enter a value.");
        }
    }
}