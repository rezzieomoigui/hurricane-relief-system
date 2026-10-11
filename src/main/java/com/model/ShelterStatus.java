package com.model;

/**
 * ShelterStatus enum specifies the status of the shelter
 * @author Fantastic Four
 */
public enum ShelterStatus {
    FULL("Full"),
    OPEN("Open"),
    NEAR_CAPACITY("Near capacity"),
    CLOSED("closed");

    public String str;

    /**
     * ShelterStatus constructor sets the string to the enum value
     * @param str string related to the enum value
     */
    private ShelterStatus(String str) {
        this.str = str;
    }
}