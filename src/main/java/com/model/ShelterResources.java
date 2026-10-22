package com.model;

/**
 * ShelterResources enum specifies the types of resources available
 * @author Fantastic Four
 */
public enum ShelterResources {
    FOOD("FOOD"),
    WATER("WATER"),
    BLANKET("BLANKET"),
    MEDICAL_EQUIPMENT("MEDICAL_EQUIPMENT"),
    CAR("CAR"),
    PET_FRIENDLY("PET FRIENDLY");

    public String str;

    /**
     * ShelterResources constructor sets the string to the enum value
     * @param str string related to the enum value
     */
    private ShelterResources(String str) {
        this.str = str;
    }

}