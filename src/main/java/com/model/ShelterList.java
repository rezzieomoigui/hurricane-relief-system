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
     * @param zipCode zip code of shelter
     * @param shelterResources shelter resources
     * @param shelterStatus shelter status
     * @param visualDescription shelter visual description
     * @return boolean true if shelter was added correctly
     */
    public boolean addShelter(UUID id, ShelterType shelterType, String address, String zipCode, ArrayList<ShelterResources> shelterResources, ShelterStatus shelterStatus, String visualDescription, int numberOfOccupants, int maxNumberOfOccupants, boolean medicalStaffAvailable) {
        if (getShelter(id) != null) {
            System.out.println("Shelter already exists");
            return false;
        }
        shelters.add(new Shelter(shelterType, address, zipCode, shelterResources, shelterStatus, visualDescription, numberOfOccupants, maxNumberOfOccupants, medicalStaffAvailable));
        return true;
    }

    /**
     * save method saves shelters
     * @return boolean depending on if it saved properly or not
     */
    public boolean save() {
        return DataWriter.saveShelters();
    }


}
