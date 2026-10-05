package com.model;

public enum ShelterType {
    HOUSE("House"),
    SCHOOL("School"),
    BUSINESS("Business"),
    COMMUNITY_CENTER("Community center");

    public String str;

    private ShelterType(String str) {
        this.str = str;
    }

}