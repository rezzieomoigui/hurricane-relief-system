package com.model;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

/**
 *  
 */
public class HurricaneReliefDriver {

    private final HurricaneReliefApplication application;
    private final Scanner scanner;
    private final HashMap<String, String> emergencyContacts;
    private final ArrayList<String> checklist;
    private final ArrayList<String> shelters;
    private final Set<Integer> completedChecklistItems;

    /**
     *  
     */
    public HurricaneReliefDriver() {
         
        application = new HurricaneReliefApplication();
        scanner = new Scanner(System.in);
        emergencyContacts = new HashMap<>();
        checklist = new ArrayList<>();
        shelters = new ArrayList<>();
        completedChecklistItems = new HashSet<>();
    }

    /**
     *  
     *
     * @param args  
     */
    public static void main(String[] args) {
        HurricaneReliefDriver driver = new HurricaneReliefDriver();
        driver.run();
    }

    /**
     *  
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
                    resetPassword();
                    break;

                case "4":
                    System.out.println("Hurricane incoming update is unavailable.");
                    break;

                case "5":
                    System.out.println("Road information is unavailable.");
                    break;

                case "6":
                    System.out.println("Hurricane preparation tips:");
                    System.out.println("- Prepare an emergency kit with water, food, and essential supplies.");
                    System.out.println("- Secure your home and bring outdoor items inside.");
                    System.out.println("- Follow official evacuation orders and monitor local alerts.");
                    break;

                case "7":
                    System.out.println("Flooding safety tips:");
                    System.out.println("- Move to higher ground and avoid walking or driving through floodwater.");
                    System.out.println("- Stay away from downed power lines and flooded areas.");
                    System.out.println("- Follow evacuation instructions and monitor official alerts.");
                    break;

                case "8":
                    System.out.println("First aid tips:");
                    System.out.println("- Check that the area is safe before approaching an injured person.");
                    System.out.println("- Call emergency services for serious injuries.");
                    System.out.println("- Apply direct pressure to control severe bleeding.");
                    System.out.println("- Follow instructions from emergency responders.");
                    break;

                case "9":
                    System.out.println("Aftermath guidelines:");
                    System.out.println("- Check for injuries and call emergency services when needed.");
                    System.out.println("- Avoid damaged buildings, downed power lines, and floodwater.");
                    System.out.println("- Follow guidance from local authorities before returning home.");
                    System.out.println("- Document damage and contact your insurer or relief agencies.");
                    break;

                case "10":
                    addToChecklist();
                    break;

                case "11":
                    markChecklistItem(true);
                    break;

                case "12":
                    markChecklistItem(false);
                    break;

                case "13":
                    registerShelter();
                    break;

                case "14":
                    System.out.println("Nearby shelters:");
                    System.out.println(getNearbyShelters());
                    break;

                case "15":
                    System.out.println(getShelterAvailability());
                    break;

                case "16":
                    reliefInquiry();
                    break;

                case "17":
                    if (emergencyContacts.isEmpty()) {
                        System.out.println(
                                "Please add an emergency contact first.");
                    } else {
                        System.out.println(
                                safeMessage(emergencyContacts));
                    }
                    break;

                case "18":
                    makeDonation();
                    break;

                case "19":
                    triggerAutomaticDonation();
                    break;

                case "20":
                    System.out.println("For technical assistance, contact your local emergency management office or the application's support team.");
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

        application.createAccount(
                userName,
                password,
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                false
        );
    }

    /**
     * Collects the credentials needed to reset a password.
     */
    private void resetPassword() {
        String userName = readText("Enter your username: ");
        String password = readText("Enter your new password: ");

        application.createAccount(
                userName,
                password,
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                false
        );
    }

    /**
     * Adds a contact and passes contacts to application to send a safe message
     */
    private void addEmergencyContact() {
        String name = readText("Enter the contact's name: ");
        String phoneNumber = readText("Enter the contact's phone number: ");

        emergencyContacts.put(name, phoneNumber);
    }

    /** Collects shelter details when registering a shelter. */
    private void registerShelter() {
        String name = readText("Enter the shelter name: ");
        String location = readText("Enter the shelter location: ");
        String capacity = readText("Enter the shelter capacity: ");
        shelters.add(name + " - " + location + " (capacity: " + capacity + ")");
        System.out.println("Shelter registration received: " + name
                + " at " + location + " (capacity: " + capacity + ").");
    }

    /** Returns the shelters registered during this session. */
    private String getNearbyShelters() {
        if (shelters.isEmpty()) {
            return "No shelters are currently registered.";
        }
        return String.join(System.lineSeparator(), shelters);
    }

    /** Returns the registered shelter capacity information. */
    private String getShelterAvailability() {
        if (shelters.isEmpty()) {
            return "No shelter availability information is currently available.";
        }
        return "Registered shelter capacity (current occupancy is unavailable):"
                + System.lineSeparator() + String.join(System.lineSeparator(), shelters);
    }

    /** Collects and validates a donation pledge. */
    private void makeDonation() {
        String amount = readText("Enter the donation amount: ");
        try {
            double donation = Double.parseDouble(amount);
            if (!Double.isFinite(donation) || donation <= 0) {
                System.out.println("Please enter a valid positive donation amount.");
                return;
            }
            System.out.printf("Donation pledge received: $%.2f%n", donation);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid donation amount.");
        }
    }

    /** Collects and validates an automatic donation pledge. */
    private void triggerAutomaticDonation() {
        String amount = readText("Enter the automatic donation amount: ");
        try {
            double donation = Double.parseDouble(amount);
            if (!Double.isFinite(donation) || donation <= 0) {
                System.out.println("Please enter a valid positive donation amount.");
                return;
            }
            System.out.printf("Automatic donation pledge set: $%.2f%n", donation);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid donation amount.");
        }
    }

    /** Collects a relief inquiry without relying on the application API. */
    private void reliefInquiry() {
        String inquiry = readText("Enter your relief inquiry: ");
        System.out.println("Relief inquiry received: " + inquiry);
    }

    /** Adds a preparation task to the checklist. */
    private void addToChecklist() {
        String item = readText("Enter a preparation checklist item: ");
        checklist.add(item);
        System.out.println("Added to checklist: " + item);
    }

    /**  
     * Marks a selected checklist item complete or incomplete.
     * @param complete whether to mark the item as complete or incomplete
     */
    private void markChecklistItem(boolean complete) {
        if (checklist.isEmpty()) {
            System.out.println("The checklist is empty. Add an item first.");
            return;
        }

        for (int i = 0; i < checklist.size(); i++) {
            String status = completedChecklistItems.contains(i) ? "[complete]" : "[incomplete]";
            System.out.println((i + 1) + ". " + status + " " + checklist.get(i));
        }

        try {
            int itemNumber = Integer.parseInt(readText("Enter the checklist item number: "));
            if (itemNumber < 1 || itemNumber > checklist.size()) {
                System.out.println("Please choose a valid checklist item number.");
                return;
            }
            int index = itemNumber - 1;
            if (complete) {
                completedChecklistItems.add(index);
                System.out.println("Checklist item marked complete.");
            } else {
                completedChecklistItems.remove(index);
                System.out.println("Checklist item marked incomplete.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    /**
     *  
     *
     * @param contacts  
     * @return  
     */
    private String safeMessage(HashMap<String, String> contacts) {
        StringBuilder message = new StringBuilder("Safe message: I am safe.\nRecipients:");
        contacts.forEach((name, phoneNumber) ->
                message.append("\n").append(name).append(" (").append(phoneNumber).append(")"));
        return message.toString();
    }

    /**
     *  
     *
     * @param prompt  
     * @return  
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