package com.model;

/**
 * ShelterResources enum specifies the types of resources available
 * @author Fantastic Four
 */
public enum ShelterResources {
    FOOD("Food"),
    WATER("Water"),
    BLANKET("Blanket"),
    MEDICAL_EQUIPMENT("Medical equipment"),
    CAR("Car"),
    PET_FRIENDLY("Pet friendly");

    public String str;

    /**
     * ShelterResources constructor sets the string to the enum value
     * @param str string related to the enum value
     */
    private ShelterResources(String str) {
        this.str = str;
    }
}