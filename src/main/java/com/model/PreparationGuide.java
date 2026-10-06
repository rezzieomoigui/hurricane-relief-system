package com.model;

public class PreparationGuide {
    private ArrayList<String> tips;
    private ArrayList<String> emergencyPhoneNumbers;

    public PreparationGuide() {
        tips = new ArrayList<>();
        emergencyPhoneNumbers = new ArrayList<>();
    }

    public ArrayList<String> searchGuidelines(String keyword) {
        // Implement search logic here
        return null;
    }

    public void selectGuide(GuideCategory category) {
        // Implement guide selection logic here
    }

    public ArrayList<String> getTips() {
        return tips;
    }

    public ArrayList<String> getEmergencyPhoneNumbers() {
        return emergencyPhoneNumbers;
    }

    public void addTip(String tip) {
        tips.add(tip);
    }

    public void addEmergencyPhoneNumber(String phoneNumber) {
        emergencyPhoneNumbers.add(phoneNumber);
    }
}