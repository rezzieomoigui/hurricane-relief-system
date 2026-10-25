package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * ShelterList method contains the shelter methods
 * @author Fantastic Four
 */
public class ShelterList {
    private static ShelterList shelterList;
    private ArrayList<Shelter> shelters;
    
    /**
     * ShelterList constructor sets the shelters into a shelterList arrayList
     */
    private ShelterList() {
        ArrayList<Shelter> loaded = DataLoader.getShelters();
        this.shelters = (loaded != null) ? loaded : new ArrayList<Shelter>();
    }

    /**
     * getInstance method returns an instance of shelter list
     * @return instance of ShelterList
     */
    public static ShelterList getInstance() {
        if (shelterList == null) {
            shelterList = new ShelterList();
        }
        return shelterList;
    }

    /**
     * getShelters returns the shelters
     * @return ArrayList of shelters
     */
    public ArrayList<Shelter> getShelters() {
        return this.shelters;
        // this needs to iterate through the shelters
    }

    /**
     * getShelter returns the shelter of a specific id
     * @param uuid user id
     * @return shelter of specific uuid
     */
    public Shelter getShelter(UUID uuid) {
        for (Shelter shelter : this.shelters) {
            if (shelter.getId().equals(uuid)) {
                return shelter;
            }
        }
        return null;
    }

    public Shelter getShelter(String address, String city, String state, String zipCode) {
        for (Shelter shelter : this.shelters) {
            if (shelter.getAddress().equals(address) && shelter.getCity().equals(city) &&
                shelter.getState().equals(state) && shelter.getZipCode().equals(zipCode)) {
                return shelter;
            }
        }
        return null;
    }

    /**
     * geShelters returns arraylist of shelters with a specific zip code
     * @param zipCode shelter zip code
     * @return array list of shelters with specific zip code
     */
    public ArrayList<Shelter> getShelter(String zipCode) {
        ArrayList<Shelter> matches = new ArrayList<Shelter>();
        for (Shelter shelter : this.shelters) {
            if (shelter.getZipCode() != null && shelter.getZipCode().equals(zipCode)) {
                matches.add(shelter);
            }
        }
        return matches;
    }

    /**
     * addShelter method adds a shelter
     * @param shelterType type of shelter
     * @param address address of shelter
     * @param city the shelter's city
     * @param state the state the shelter is in
     * @param zipCode zip code of shelter
     * @param shelterResources shelter resources
     * @param occupants All occupants of shelter
     * @param numberOfOccupants Total number of occupants
     * @param maxNumberOfOccupants Max occupants allowed
     * @param shelterStatus shelter status
     * @param visualDescription shelter visual description
     * @return boolean true if shelter was added correctly
     */
    public Shelter addShelter(ShelterType shelterType, String address, String city, String state, String zipCode, ArrayList<ShelterResources> shelterResources, 
        ShelterStatus shelterStatus, String visualDescription, int numberOfOccupants, int maxNumberOfOccupants, boolean medicalStaffAvailable, boolean isPetFriendly) {
        Shelter shelter = new Shelter(shelterType, address, city, state, zipCode, shelterResources, shelterStatus, visualDescription, numberOfOccupants, maxNumberOfOccupants, medicalStaffAvailable, isPetFriendly);
        shelters.add(shelter);
        return shelter;
    }

    /**
     * addPartyMember adds a user to the relief request
     * @param user account user
     * @return boolean based on if party member was correctly added
     */
    public void addOccupants(Shelter shelter, ArrayList<PartyMember> newOccupants) {
        ArrayList<PartyMember> occupants = shelter.getOccupants();
        for (PartyMember occupant : newOccupants) {
            occupants.add(new PartyMember(occupant.getName(), occupant.getYearBorn(), occupant.needsMedicalAssistance(), occupant.getType()));
        }
        shelter.setOccupants(occupants);
    }

    /**
     * save method saves shelters
     * @return boolean depending on if it saved properly or not
     */
    public boolean save() {
        return DataWriter.saveShelters();
    }


}
