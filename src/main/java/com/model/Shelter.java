package com.model;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Shelter class creates a shelter
 * @author Fantastic Four
 */
public class Shelter {
    private UUID uuid;
    private String address;
    private String zipCode;
    private ShelterType shelterType;
    private ShelterStatus shelterStatus;
    private ShelterResources shelterResources;
    private String visualDescription;

    /**
     * Shelter constructor creates a brand new shelter and generates its id.
     */
    public Shelter(ShelterType shelterType, String address, String zipCode,
                   ShelterResources shelterResources, ShelterStatus shelterStatus,
                   String visualDescription) {
        this(UUID.randomUUID(), shelterType, address, zipCode,
                shelterResources, shelterStatus, visualDescription);
    }

    /**
     * Shelter constructor recreates an existing shelter with a known id (used when loading from JSON).
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
     * searchByZip method returns a list containing shelters in a specific zip code
     * @return arrayList of shelters with a specific zip code
     */
    public ArrayList<Shelter> searchByZip(String zipCode) {
        ArrayList<Shelter> results = new ArrayList<>();
        if (zipCode != null && zipCode.equals(this.zipCode)) {
            results.add(this);
        }
        return results;
    }

    /**
     * getID returns the user id
     * @return user uuid
     */
    public UUID getId() {
        return uuid;
    }

    /**
     * getAddress returns the user address
     * @return user address
     */
    public String getAddress() {
        return address;
    }

    /**
     * setAddress sets the address of the user
     * @param address user address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * getZipCode returns the user zip code
     * @return user zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * setZipCode sets the zip code of the user
     * @param zipCode user zip code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * getShelterType return the ShelterType of the shelter
     * @return shelter ShelterType
     */
    public ShelterType getShelterType() {
        return shelterType;
    }

    /**
     * setShelterType sets the shelter type
     * @param shelterType shelter type
     */
    public void setShelterType(ShelterType shelterType) {
        this.shelterType = shelterType;
    }

    /**
     * getShelterStatus returns the shelter status
     * @return shelter status
     */
    public ShelterStatus getShelterStatus() {
        return shelterStatus;
    }

    /**
     * setShelterStatus sets the shelter status
     * @param shelterStatus shelter status
     */
    public void setShelterStatus(ShelterStatus shelterStatus) {
        this.shelterStatus = shelterStatus;
    }

    /**
     * getShelterResources returns shelter resources
     * @return shelter resources
     */
    public ShelterResources getShelterResources() {
        return shelterResources;
    }

    /**
     * setShelterResources sets shelter resources
     * @param shelterResources shelter resources
     */
    public void setShelterResources(ShelterResources shelterResources) {
        this.shelterResources = shelterResources;
    }

    /**
     * getVisualDescription returns the shelter visual description
     * @return shelter visual description
     */
    public String getVisualDescription() {
        return visualDescription;
    }

    /**
     * setVisualDescription sets the visual description of the shelter
     * @param visualDescription shelter visual description
     */
    public void setVisualDescription(String visualDescription) {
        this.visualDescription = visualDescription;
    }

    /**
     * isAvailable method checks if the shelter is available
     * @return boolean for if the shelter is available
     */
    public boolean isAvailable() {
        return shelterStatus == ShelterStatus.OPEN
                || shelterStatus == ShelterStatus.NEAR_CAPACITY;
    }

    /**
     * toString prints the type, address, zip code, status, and resources of the shelter
     * @return String presenting shelter information
     */
    @Override
    public String toString() {
        return shelterType + " at " + address + " " + zipCode
                + " [" + shelterStatus + ", " + shelterResources + "]";
    }
}