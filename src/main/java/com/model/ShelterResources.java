package com.model;

public enum ShelterResources {
    FOOD("Food"),
    WATER("Water"),
    BLANKET("Blanket"),
    MEDICAL_EQUIPMENT("Medical equipment"),
    CAR("Car"),
    PET_FRIENDLY("Pet friendly");

    public String str;

    private ShelterResources(String str) {
        this.str = str;
    }
}