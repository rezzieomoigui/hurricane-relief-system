package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class ShelterList {
    private static ShelterList shelterList;
    private ArrayList<Shelter> shelters;
    
    private ShelterList() {
        shelterList = new ShelterList();
        this.shelters = new ArrayList<Shelter>();
    }

    public static ShelterList getInstance() {
        return shelterList;
    }

    public ArrayList<Shelter> getShelters() {
        return this.shelters;
    }

    public Shelter getShelter(UUID uuid) {
        for (Shelter shelter : this.shelters) {
            if (shelter.getUUID().equals(uuid)) {
                return shelter;
            }
        }
        return null;
    }

    /**public ArrayList<Shelter> getShelter(String zipCode) {
        return Shelter();
    }**/

    public boolean addShelter(ShelterType shelterType, String address, String zipCode, ShelterResources shelterResources, ShelterStatus shelterStatus, String visual description) {
        return true;
    }


}
