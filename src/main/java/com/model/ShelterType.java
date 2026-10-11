package com.model;

/**
 * ShelterType enum specific the type of shelter
 * @author Fantastic Four
 */
public enum ShelterType {
    HOUSE("House"),
    SCHOOL("School"),
    BUSINESS("Business"),
    COMMUNITY_CENTER("Community center");

    public String str;

    /**
     * ShelterType constructor sets the string to the enum value
     * @param str string related to the enum value
     */
    private ShelterType(String str) {
        this.str = str;
    }

}