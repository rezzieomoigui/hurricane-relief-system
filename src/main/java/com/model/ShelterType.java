package com.model;

/**
 * ShelterType enum specific the type of shelter
 * @author Fantastic Four
 */
public enum ShelterType {
    HOUSE("HOUSE"),
    SCHOOL("SCHOOL"),
    BUSINESS("BUSINESS"),
    COMMUNITY_CENTER("COMMUNITY_CENTER");

    public String str;

    /**
     * ShelterType constructor sets the string to the enum value
     * @param str string related to the enum value
     */
    private ShelterType(String str) {
        this.str = str;
    }

}