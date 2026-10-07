package com.model;
import java.util.ArrayList;
import java.util.UUID;
public class Shelter {
    private UUID uuid;
    private String address;
    private String zipCode;
    private ShelterType shelterType;
    private ShelterStatus shelterStatus;
    private ShelterResources shelterResources;
    private String visualDescription;

    /**
     * Creates a brand new shelter and generates its id.
     */
    public Shelter(ShelterType shelterType, String address, String zipCode,
                   ShelterResources shelterResources, ShelterStatus shelterStatus,
                   String visualDescription) {
        this(UUID.randomUUID(), shelterType, address, zipCode,
                shelterResources, shelterStatus, visualDescription);
    }

    /**
     * Recreates an existing shelter with a known id (used when loading from JSON).
     */
    public Shelter(UUID id, ShelterType shelterType, String address, String zipCode,
                   ShelterResources shelterResources, ShelterStatus shelterStatus,
                   String visualDescription) {
        this.uuid = id;
        this.shelterType = shelterType;
        this.address = address;
        this.zipCode = zipCode;
        this.shelterResources = shelterResources;
        this.shelterStatus = shelterStatus;
        this.visualDescription = visualDescription;
    }

    /**
     * Returns a list containing this shelter if it is in the given zip code,
     * otherwise an empty list. Searching across all shelters belongs in
     * ShelterList.getShelter(String zipCode).
     */
    public ArrayList<Shelter> searchByZip(String zipCode) {
        ArrayList<Shelter> results = new ArrayList<>();
        if (zipCode != null && zipCode.equals(this.zipCode)) {
            results.add(this);
        }
        return results;
    }

    public UUID getId() {
        return uuid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public ShelterType getShelterType() {
        return shelterType;
    }

    public void setShelterType(ShelterType shelterType) {
        this.shelterType = shelterType;
    }

    public ShelterStatus getShelterStatus() {
        return shelterStatus;
    }

    public void setShelterStatus(ShelterStatus shelterStatus) {
        this.shelterStatus = shelterStatus;
    }

    public ArrayList<ShelterResources> getShelterResources() {
        return shelterResources;
    }

    public void setShelterResources(ArrayList<ShelterResources> shelterResources) {
        this.shelterResources = shelterResources;
    }

    public String getVisualDescription() {
        return visualDescription;
    }

    public void setVisualDescription(String visualDescription) {
        this.visualDescription = visualDescription;
    }

    /**
     * True if the shelter can currently take people in.
     */
    public boolean isAvailable() {
        return shelterStatus == ShelterStatus.OPEN
                || shelterStatus == ShelterStatus.NEAR_CAPACITY;
    }

    @Override
    public String toString() {
        return shelterType + " at " + address + " " + zipCode
                + " [" + shelterStatus + ", " + shelterResources + "]";
    }
}