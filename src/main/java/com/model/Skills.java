package com.model;

/**
 * Skills enum specifies the skills that the user has
 * @author Fantastic Four
 */
public enum Skills {
    WOUND_CARE("WOUND_CARE"),
    CPR("CPR"),
    SEARCH_AND_RESCUE("SEARCH_AND_RESCUE"),
    FLOOD_RESCUE("FLOOD_RESCUE");

    public String str;

    private Skills(String str) {
        this.str = str;
    }
}