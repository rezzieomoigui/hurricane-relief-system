package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Shelter class creates a shelter
 * @author Fantastic Four
 */
public class Shelter {
    private UUID id;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private ShelterType shelterType;
    private ShelterStatus shelterStatus;
    private ArrayList<ShelterResources> shelterResources;
    private String visualDescription;
    private ArrayList<PartyMember> occupants;
    private int numberOfOccupants;
    private int maxNumberOfOccupants;
    private boolean medicalStaffAvailable;
    private boolean isPetFriendly;

    /**
     * Creates a new shelter and generates its id
     * @param shelterType shelter type
     * @param address shelter address
     * @param city The city the shelter is in
     * @param state The state the shelter is in
     * @param zipCode shelter zip code
     * @param shelterResources shelter resources
     * @param shelterStatus shelter status
     * @param visualDescription shelter visual description
     * @param occupants Occupants of the shelter
     * @param numberOfOccupants number of people currently at shelter
     * @param maxNumberOfOccupants maximum number of people shelter can hold
     * @param medicalStaffAvailable checks if shelter has medical staff available
     * @param isPetFriendly checks if pets are allowed
     */
    public Shelter(ShelterType shelterType, String address, String city, String state, String zipCode,
                   ArrayList<ShelterResources> shelterResources,
                   ShelterStatus shelterStatus, String visualDescription, ArrayList<PartyMember> occupants, int numberOfOccupants,
                   int maxNumberOfOccupants, boolean medicalStaffAvailable, boolean isPetFriendly) {
        UUID.randomUUID();
        this.shelterType = shelterType;
        this.address = address;
        this.city = city;
        this.state = state;
        this.shelterResources = shelterResources;
        this.shelterStatus = shelterStatus;
        this.visualDescription = visualDescription;
        this.occupants = occupants;
        this.numberOfOccupants = numberOfOccupants;
        this.maxNumberOfOccupants = maxNumberOfOccupants;
        this.medicalStaffAvailable = medicalStaffAvailable;
        this.isPetFriendly = isPetFriendly;
    }

    /**
     * Recreates an existing shelter with a known id and occupant counts (used when loading frm JSON)
     * @param id shelter id
     * @param shelterType shelter type
     * @param address shelter address
     * @param city The city the shelter is in
     * @param state The state the shelter is in
     * @param zipCode shelter zip code
     * @param shelterResources shelter resources
     * @param shelterStatus shelter status
     * @param visualDescription shelter visual description
     * @param occupants Occupants of the shelter
     * @param numberOfOccupants number of people currently at shelter
     * @param maxNumberOfOccupants maximum number of people shelter can hold
     * @param medicalStaffAvailable checks if shelter has medical staff available
     * @param isPetFriendly checks if pets are allowed
     */
    public Shelter(UUID id, ShelterType shelterType, String address, String city, String state,
                   String zipCode, ArrayList<ShelterResources> shelterResources,
                   ShelterStatus shelterStatus, String visualDescription, ArrayList<PartyMember> occupants,
                   int numberOfOccupants, int maxNumberOfOccupants, boolean medicalStaffAvailable, boolean isPetFriendly) {

        if (numberOfOccupants < 0 || maxNumberOfOccupants < 0
                || numberOfOccupants > maxNumberOfOccupants) {
            throw new IllegalArgumentException(
                    "Invalid shelter occupancy.");
        }

        this.id = id;
        this.shelterType = shelterType;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.shelterResources = shelterResources;
        this.shelterStatus = shelterStatus;
        this.visualDescription = visualDescription;
        this.occupants = occupants;
        this.numberOfOccupants = numberOfOccupants;
        this.maxNumberOfOccupants = maxNumberOfOccupants;
        this.medicalStaffAvailable = medicalStaffAvailable;
        this.isPetFriendly = isPetFriendly;
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
        return id;
    }

    /**
     * Returns the shelter address.
     * @return shelter address
     */
    public String getAddress() {
        return address;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
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

    public ShelterStatus getStatus() {
        return this.shelterStatus;
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

    public boolean medicalStaffAvailable() {
        return this.medicalStaffAvailable;
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
    public void setNumberOfOccupants(int numberOfOccupants, boolean isAdmin) {
        if (!isAdmin)
            return;
        if (numberOfOccupants < 0
                || numberOfOccupants > maxNumberOfOccupants) {
            throw new IllegalArgumentException(
                    "Occupants must be between 0 and maximum capacity.");
        }
        else if (numberOfOccupants == this.maxNumberOfOccupants) 
            setShelterStatus(ShelterStatus.FULL);
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

    public ArrayList<PartyMember> getOccupants() {
        return this.occupants;
    }

    /**
     * Checks if the shelter is available.
     * @return true if the shelter is open or near capacity
     */
    public boolean isAvailable() {
        return shelterStatus == ShelterStatus.OPEN;
    }

    public boolean isPetFriendly() {
        return this.isPetFriendly;
    }

    /**
     * Returns the shelter information as a string.
     * @return string presenting shelter information
     */
    @Override
    public String toString() {
        return "\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" +
        "Address: "+this.address+", "+this.city+", "+ this.state+", "+this.zipCode + "\n" + this.shelterType +
        "\nStatus: "+this.getStatus().str+"\nDescription: "+this.visualDescription+"\nNumber of occupants: "+this.numberOfOccupants+
        "\nMaximum number of occupants: "+this.maxNumberOfOccupants+"\nMedical staff available? "+this.medicalStaffAvailable+"\nPet friendly? "+this.isPetFriendly()+
        "\nShelter Resources: "+this.shelterResources.toString()+occupantsToString() + 
        "\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";

    }

    private String occupantsToString() {
        String str = "\n\nOCCUPANTS\n";
        int age;
        for ( PartyMember partyMember : this.occupants ) {
            age = 2026 - Integer.parseInt(partyMember.getYearBorn());
            str += "----------------------------------------------" + "\nname: "+partyMember.getName()+"\nage: "+age+"\nneeds medical assistance? "+partyMember.needsMedicalAssistance()+"\nI am a: "+partyMember.getType().str+"\n";
        }
        return str;
    }
}
