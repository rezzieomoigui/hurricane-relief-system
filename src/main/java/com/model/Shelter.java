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
    private ArrayList<ShelterResources> shelterResources;
    private String visualDescription;
    private int numberOfOccupants;
    private int maxNumberOfOccupants;

    /**
     * Creates a new shelter and generates its id.
     */
    public Shelter(ShelterType shelterType, String address, String zipCode,
                   ArrayList<ShelterResources> shelterResources,
                   ShelterStatus shelterStatus, String visualDescription) {
        this(UUID.randomUUID(), shelterType, address, zipCode,
                shelterResources, shelterStatus, visualDescription, 0, 10);
    }

    /**
     * Recreates an existing shelter with a known id (used when loading from JSON).
     */
    public Shelter(UUID id, ShelterType shelterType, String address,
                   String zipCode,
                   ArrayList<ShelterResources> shelterResources,
                   ShelterStatus shelterStatus, String visualDescription) {
        this(id, shelterType, address, zipCode, shelterResources,
                shelterStatus, visualDescription, 0, 10);
    }

    /**     * Creates a new shelter with occupant counts.
     */
    public Shelter(ShelterType shelterType, String address, String zipCode,
                   ArrayList<ShelterResources> shelterResources,
                   ShelterStatus shelterStatus, String visualDescription,
                   int numberOfOccupants, int maxNumberOfOccupants) {
        this(UUID.randomUUID(), shelterType, address, zipCode,
                shelterResources, shelterStatus, visualDescription,
                numberOfOccupants, maxNumberOfOccupants);
    }

    /**
     * Recreates an existing shelter with its id and occupant counts.
     */
    public Shelter(UUID id, ShelterType shelterType, String address,
                   String zipCode,
                   ArrayList<ShelterResources> shelterResources,
                   ShelterStatus shelterStatus, String visualDescription,
                   int numberOfOccupants, int maxNumberOfOccupants) {

        if (numberOfOccupants < 0 || maxNumberOfOccupants < 0
                || numberOfOccupants > maxNumberOfOccupants) {
            throw new IllegalArgumentException(
                    "Invalid shelter occupancy.");
        }

        this.uuid = id;
        this.shelterType = shelterType;
        this.address = address;
        this.zipCode = zipCode;
        this.shelterResources = shelterResources;
        this.shelterStatus = shelterStatus;
        this.visualDescription = visualDescription;
        this.numberOfOccupants = numberOfOccupants;
        this.maxNumberOfOccupants = maxNumberOfOccupants;
    }

    /**
     * Returns shelters matching a specific zip code.
     * @param zipCode zip code to search for
     * @return list of matching shelters
     */
    public ArrayList<Shelter> searchByZip(String zipCode) {
        ArrayList<Shelter> results = new ArrayList<>();
        if (zipCode != null && zipCode.equals(this.zipCode)) {
            results.add(this);
        }
        return results;
    }

    /**
     * Returns the shelter id.
     * @return shelter UUID
     */
    public UUID getId() {
        return uuid;
    }

    /**
     * Returns the shelter address.
     * @return shelter address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the shelter address.
     * @param address shelter address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the shelter zip code.
     * @return shelter zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the shelter zip code.
     * @param zipCode shelter zip code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Returns the shelter type.
     * @return shelter type
     */
    public ShelterType getShelterType() {
        return shelterType;
    }

    /**
     * Sets the shelter type.
     * @param shelterType shelter type
     */
    public void setShelterType(ShelterType shelterType) {
        this.shelterType = shelterType;
    }

    /**
     * Returns the shelter status.
     * @return shelter status
     */
    public ShelterStatus getShelterStatus() {
        return shelterStatus;
    }

    /**
     * Sets the shelter status.
     * @param shelterStatus shelter status
     */
    public void setShelterStatus(ShelterStatus shelterStatus) {
        this.shelterStatus = shelterStatus;
    }

    /**
     * Returns shelter resources.
     * @return shelter resources
     */
    public ArrayList<ShelterResources> getShelterResources() {
        return shelterResources;
    }

    /**
     * Sets shelter resources.
     * @param shelterResources shelter resources
     */
    public void setShelterResources(
            ArrayList<ShelterResources> shelterResources) {
        this.shelterResources = shelterResources;
    }

    /**
     * Returns the shelter visual description.
     * @return visual description
     */
    public String getVisualDescription() {
        return visualDescription;
    }

    /**
     * Sets the shelter visual description.
     * @param visualDescription visual description
     */
    public void setVisualDescription(String visualDescription) {
        this.visualDescription = visualDescription;
    }

    /**
     * Returns the current number of occupants.
     * @return number of occupants
     */
    public int getNumberOfOccupants() {
        return numberOfOccupants;
    }

    /**
     * Sets the current number of occupants.
     * @param numberOfOccupants number of occupants
     */
    public void setNumberOfOccupants(int numberOfOccupants) {
        if (numberOfOccupants < 0
                || numberOfOccupants > maxNumberOfOccupants) {
            throw new IllegalArgumentException(
                    "Occupants must be between 0 and maximum capacity.");
        }
        this.numberOfOccupants = numberOfOccupants;
    }

    /**
     * Returns the maximum number of occupants.
     * @return maximum capacity
     */
    public int getMaxNumberOfOccupants() {
        return maxNumberOfOccupants;
    }

    /**
     * Sets the maximum number of occupants.
     * @param maxNumberOfOccupants maximum capacity
     */
    public void setMaxNumberOfOccupants(int maxNumberOfOccupants) {
        if (maxNumberOfOccupants < 0
                || maxNumberOfOccupants < numberOfOccupants) {
            throw new IllegalArgumentException(
                    "Maximum capacity cannot be below current occupancy.");
        }
        this.maxNumberOfOccupants = maxNumberOfOccupants;
    }

    /**
     * Checks if the shelter is available.
     * @return true if the shelter is open or near capacity
     */
    public boolean isAvailable() {
        return shelterStatus == ShelterStatus.OPEN
                || shelterStatus == ShelterStatus.NEAR_CAPACITY;
    }

    /**
     * Returns the shelter information as a string.
     * @return string presenting shelter information
     */
    @Override
    public String toString() {
        return shelterType + " at " + address + " " + zipCode
                + " [" + shelterStatus
                + ", Occupants: " + numberOfOccupants
                + "/" + maxNumberOfOccupants
                + ", Resources: " + shelterResources + "]";
    }
}
