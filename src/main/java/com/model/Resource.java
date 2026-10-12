package com.model;

public enum Resource {
    WATER_BOTTLE("Water bottle"),
    CANNED_GOODS("Canned goods"),
    FLASHLIGHT("Flashlight"),
    PORTABLE_CHARGER("Portable charge");

    public String str;

    private Resource(String str) {
        this.str = str;
    }
}