package com.model;
import java.util.UUID;
public class Shelter {
    private UUID uuid;
    private String address;
    private String zipCode;
    private ShelterType shelterType;
    private ShelterStatus shelterStatus;
    private ShelterResources shelterResources;
    private String visualDiscription;

    public Shelter(UUID uuid, String address, String zipCode, ShelterType shelterType, ShelterStatus shelterStatus,
                   ShelterResources shelterResources, String visualDiscription) {
        
        this.uuid = uuid;
        this.address = address;
        this.zipCode = zipCode;
        this.shelterType = shelterType;
        this.shelterStatus = shelterStatus;
        this.shelterResources = shelterResources;
        this.visualDiscription = visualDiscription;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public searchByZip(String zipCode) {
        // Implementation for searching shelters by zip code
    }
}