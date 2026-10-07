package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class ShelterList {
    private static ShelterList shelterList;
    private ArrayList<Shelter> shelters;
    
    private ShelterList() {
        this.shelters = new ArrayList<Shelter>();
    }

    public static ShelterList getInstance() {
        if (shelterList == null) {
            shelterList = new ShelterList();
        }
        return shelterList;
    }

    public ArrayList<Shelter> getShelters() {
        return this.shelters;
    }

    public Shelter getShelter(UUID uuid) {
        for (Shelter shelter : this.shelters) {
            if (shelter.getId().equals(uuid)) {
                return shelter;
            }
        }
        return null;
    }

    public ArrayList<Shelter> getShelter(String zipCode) {
        return null;
    }

    public boolean addShelter(ShelterType shelterType, String address, String zipCode, ArrayList<ShelterResources> shelterResources, ShelterStatus shelterStatus, String visualDescription) {
        this.shelters.add(new Shelter(shelterType, address, zipCode, shelterResources, shelterStatus, visualDescription));
        return true;
    }


}
