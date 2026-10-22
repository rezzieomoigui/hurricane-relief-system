package com.model;

/**
 * Preparation enum specifies the ways for people to prepare
 * @author Fantastic Four
 */
public enum Preparation {
    PETS_INSIDE("PET_INSIDE"),
    PET_FOOD("PET_FOOD"),
    PET_CARRIER("PET_CARRIER"),
    CLOSE_WINDOWS("CLOSE_WINDOWS"),
    DOORS_LOCKED("DOORS_LOCKED"),
    FLASHLIGHT("FLASHLIGHT"),
    WATER("WATER"),
    CANNED_GOODS("CANNED_GOODS"),
    DIAPERS("DIAPERS"),
    FORMULA("FORMULA"),
    TOILET_PAPER("TOILET_PAPER"),
    FIRST_AID_KIT("FIRST_AID_KIT"),
    MEDICATIONS("MEDICATIONS"),
    MEDICAL_EQUIPMENT("MEDICAL_EQUIPMENT"),
    EVACUATION_PLAN("EVACUATION_PLAN"),
    CHARGED_DEVICES("CHARGED_DEVICES"),
    DOCUMENTS_LOCATED("DOCUMENTS_LOCATED");

    private final String str;
/**
 * Preparation constructor sets the string to the enum value
 * @param str string related to the enum value
 */
    Preparation(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}