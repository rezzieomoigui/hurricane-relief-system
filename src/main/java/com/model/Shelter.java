package com.model;

public class Shelter {
    private String address;
    private String zipCode;
    private ShelterType shelterType;
    private ShelterStatus shelterStatus;
    private ShelterResources shelterResources;
    private String visualDiscription;

    public Shelter(String address, String zipCode, ShelterType shelterType, ShelterStatus shelterStatus,
                   ShelterResources shelterResources, String visualDiscription) {
        this.address = address;
        this.zipCode = zipCode;
        this.shelterType = shelterType;
        this.shelterStatus = shelterStatus;
        this.shelterResources = shelterResources;
        this.visualDiscription = visualDiscription;
    }

    public searchByZip(String zipCode) {
        // Implementation for searching shelters by zip code
    }
}