package com.model;

/**
 * ShelterStatus enum specifies the status of the shelter
 * @author Fantastic Four
 */
public enum ShelterStatus {
    FULL("FULL"),
    OPEN("OPEN"),
    CLOSED("CLOSED");

    public String str;

    /**
     * ShelterStatus constructor sets the string to the enum value
     * @param str string related to the enum value
     */
    private ShelterStatus(String str) {
        this.str = str;
    }
}