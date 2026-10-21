package com.model;

import java.util.ArrayList;

/**
 * PreparationGuide class allows the user to access informational guidelines
 * @author Fantastic Four
 */
public class PreparationGuide {
    private ArrayList<String> tips;
    private ArrayList<String> emergencyPhoneNumbers;

    /**
     * PreparationGuide constructor creates a preparation guide
     */
    public PreparationGuide() {
        tips = new ArrayList<>();
        emergencyPhoneNumbers = new ArrayList<>();
    }

    /**
     * seachGuidelines method searches for hurricane guidelines
     * @param keyword guidelines keyword
     * @return ArrayList of guidelines to follow
     */
    public ArrayList<String> searchGuidelines(String keyword) {
        for (int i = 0; i < tips.length(); i++) {

        }
    }

    /**
     * getTips method gets tips for the user
     * @return ArrayList of tips for the user
     */
    public ArrayList<String> getTips() {
        return tips;
    }

    /**
     * getEmergencyPhoneNumbers gets the emergency contact phone numbers of the user
     * @return arrayList of emergency phone numbers the user has saved
     */
    public ArrayList<String> getEmergencyPhoneNumbers() {
        return emergencyPhoneNumbers;
    }

    /**
     * addTip method adds a tip to the existing list of tips
     * @param tip suggestion for user
     */
    public void addTip(String tip) {
        tips.add(tip);
    }

    /**
     * addEmergencyPhoneNumber adds an emergency phone number
     * @param phoneNumber phone number of emergency contact
     */
    public void addEmergencyPhoneNumber(String phoneNumber) {
        emergencyPhoneNumbers.add(phoneNumber);
    }
}