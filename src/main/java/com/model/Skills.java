package com.model;

/**
 * Skills enum specifies the skills that the user has
 * @author Fantastic Four
 */
public enum Skills {
    WOUND_CARE("Wound care"),
    CPR("CPR"),
    SEARCH_AND_RESCUE("Search and rescue"),
    FLOOD_RESCUE("Flood rescue");

    public String str;

    private Skills(String str) {
        this.str = str;
    }
}