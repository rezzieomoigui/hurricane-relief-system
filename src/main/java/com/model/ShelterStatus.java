package com.model;

public enum ShelterStatus {
    FULL("Full"),
    OPEN("Open"),
    NEAR_CAPACITY("Near capacity"),
    CLOSED("closed");

    public String str;

    private ShelterStatus(String str) {
        this.str = str;
    }
}