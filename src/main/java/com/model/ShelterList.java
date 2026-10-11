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
        this.shelters = new ArrayList<Shelter>();
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

    /**
     * geShelters returns arraylist of shelters with a specific zip code
     * @param zipCode shelter zip code
     * @return array list of shelters with specific zip code
     */
    public ArrayList<Shelter> getShelter(String zipCode) {
        return null;
    }

    /**
     * addShelter method adds a shelter
     * @param shelterType type of shelter
     * @param address address of shelter
     * @param zipCode zip code of shelter
     * @param shelterResources shelter resources
     * @param shelterStatus shelter status
     * @param visualDescription shelter visual description
     * @return boolean true if shelter was added correctly
     */
    public boolean addShelter(ShelterType shelterType, String address, String zipCode, ShelterResources shelterResources, ShelterStatus shelterStatus, String visualDescription) {
        this.shelters.add(new Shelter(shelterType, address, zipCode, shelterResources, shelterStatus, visualDescription));
        return true;
        // add case for if shelter doesn't exist
    }


}
