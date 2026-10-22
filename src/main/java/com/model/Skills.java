package com.model;

/**
 * Skills enum specifies the skills that the user has
 * @author Fantastic Four
 */
public enum Skills {
    WOUND_CARE("WOUND_CARE"),
    CPR("CPR"),
    SEARCH_AND_RESCUE("SEARCH_AND_RESCUE"),
    FLOOD_RESCUE("FLOOD_RESCUE"),
    TRANSPORTATION("TRANSPORTATION");

    public String str;
/**
 * Skills constructor sets the string to the enum value
 * @param str string related to the enum value
 */
    private Skills(String str) {
        this.str = str;
    }
}